package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.EncounterRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.PatientRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FollowUpService {
    private FollowUpRepository followUpRepository;
    private PatientRepository patientRepository;
    private EncounterRepository encounterRepository;
    private SupervisorService supervisorService;
    private FieldWorkerService fieldWorkerService;
    private DoctorService doctorService;
    private ValidationHelper validationHelper;

    @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;

    public FollowUpService(FollowUpRepository followUpRepository, PatientRepository patientRepository, EncounterRepository encounterRepository, SupervisorService supervisorService, FieldWorkerService fieldWorkerService, DoctorService doctorService, ValidationHelper validationHelper) {
        this.followUpRepository = followUpRepository;
        this.patientRepository = patientRepository;
        this.encounterRepository = encounterRepository;
        this.supervisorService = supervisorService;
        this.fieldWorkerService = fieldWorkerService;
        this.doctorService = doctorService;
        this.validationHelper = validationHelper;
    }

    @PostConstruct
    public void setup(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println(ACCOUNT_SID);
        System.out.println((AUTH_TOKEN));
    }

    public List<Integer> updateFollowUp(List<FollowUp> followUpList) throws RuntimeException{

        List<Integer> followUpListUpdatedId = new ArrayList<>();

        for(int i=0; i<followUpList.size(); i++) {
            FollowUp incomingFollowUp = followUpList.get(i);
            Optional<FollowUp> updatedFollowUp = followUpRepository.findById(incomingFollowUp.getFollowUpId());

            if (updatedFollowUp.isEmpty()) {
                throw new ResourceNotFoundException("Follow up with id: " + incomingFollowUp.getFollowUpId() + " not found");
            }

            updatedFollowUp.get().setFlag(true);
            updatedFollowUp.get().setFieldWorkerRemarks(incomingFollowUp.getFieldWorkerRemarks());
//            updatedFollowUp.get().setReadings(incomingFollowUp.getReadings());

            updatedFollowUp.get().getReadings().setSugar(incomingFollowUp.getReadings().getSugar());
            updatedFollowUp.get().getReadings().setTemperature(incomingFollowUp.getReadings().getTemperature());
            updatedFollowUp.get().getReadings().setBloodPressure(incomingFollowUp.getReadings().getBloodPressure());

            followUpListUpdatedId.add(updatedFollowUp.get().getFollowUpId());

            followUpRepository.save(updatedFollowUp.get());
        }
        return followUpListUpdatedId;
    }

    public List<FollowUp> getAllFollowUpsUnderSupervisor(String userId){

        validationHelper.usernamePasswordValidation(userId);

        Supervisor supervisor = supervisorService.getSupervisorByUserId(userId);

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = supervisor.getHospitalList();

//        hospitalList.forEach(hospital -> followUpRepository.findByHospitalAndFlagIsFalse(hospital).forEach(followUp -> followUpList.add(followUp)));

        hospitalList.forEach(hospital -> {followUpList.addAll(followUpRepository.findByHospitalAndFlagIsFalse(hospital));});

        return followUpList;
    }

    public void addFollowUps(Encounter encounter){
        List<FollowUp> followUpList = encounter.getFollowUpList();
        Encounter encounter1 = encounterRepository.findById(encounter.getEncounterId()).orElseThrow();
        for (FollowUp followUp : followUpList) {
            followUp.setEncounter(encounter1);
            followUp.setFlag(false);
            followUp.setPatient(encounter1.getPatient());
            followUp.setHospital(encounter1.getDoctor().getHospital());
            followUp.setOtp((int)((Math.random() * (9999 - 1000)) + 1000));
            followUpRepository.save(followUp);
        }
    }

    public List<FollowUp> getFollowUpsByFieldWorker(int fieldWorkerId){

        FieldWorker fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerId);
        List<Patient> patientList = patientRepository.findByFieldWorker(fieldWorker);

        List<FollowUp> followUpList = new ArrayList<>();

        for(int i=0; i<patientList.size(); i++){
            List<FollowUp> followUpListForPatient = followUpRepository.findByPatient(patientList.get(i));
            followUpList.addAll(followUpListForPatient);
        }

        return followUpList;
    }

    public List<FollowUp> getFollowUpsByFieldWorkerMobile(String fieldWorkerUsername, int followUpId){
        validationHelper.usernamePasswordValidation(fieldWorkerUsername);
        List<FollowUp> followUpList = new ArrayList<>();
        FieldWorker fieldWorker = fieldWorkerService.getFieldWorkerByUsername(fieldWorkerUsername);
        followUpList = getFollowUpsByFieldWorker(fieldWorker.getAuthId());

        List<FollowUp> followUpListById = new ArrayList<>();

        for(int i=0; i<followUpList.size(); i++){
            if(followUpList.get(i).getFollowUpId() > followUpId){
                followUpListById.add(followUpList.get(i));
            }
            if(followUpListById.size() == 5)
                break;
        }

        return followUpListById;
    }

    public List<FollowUp> getAllFollowUpsAssignedByDoctor(String doctorUserId) throws RuntimeException{

        validationHelper.usernamePasswordValidation(doctorUserId);

        Doctor doctor= doctorService.getDoctorByUserId(doctorUserId);

        List<Encounter> encounterList= doctor.getEncounterList();

        List<FollowUp> followUpList= new ArrayList<>();

        encounterList.forEach(encounter -> followUpList.addAll(encounter.getFollowUpList()));

        return followUpList;
    }

    @Scheduled(cron = "0 46 11 * * ?")
    public void sendOtpForTodaysFollowUps(){
        String date = LocalDate.now().toString();
        List<FollowUp> followUpList = followUpRepository.findByDate(date);

//        System.out.println("Sending message");
//        Message message= Message.creator(
//                new PhoneNumber("+919015346166"),
//                new PhoneNumber(OUTGOING_SMS_NUMBER),
//                "Your OTP for today's follow up is ").create();


        followUpList.forEach((followUp -> {
            Message message= Message.creator(
                    new PhoneNumber("+91"+ followUp.getPatient().getContact()),
                    new PhoneNumber(OUTGOING_SMS_NUMBER),
                    "Your OTP for today's follow up is "+ followUp.getOtp()).create();
        }));
    }
}
