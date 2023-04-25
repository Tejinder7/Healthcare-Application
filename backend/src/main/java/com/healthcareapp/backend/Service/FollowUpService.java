package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.EncounterRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.PatientRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.stereotype.Component;

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

    public FollowUpService(FollowUpRepository followUpRepository, PatientRepository patientRepository, EncounterRepository encounterRepository, SupervisorService supervisorService, FieldWorkerService fieldWorkerService, DoctorService doctorService, ValidationHelper validationHelper) {
        this.followUpRepository = followUpRepository;
        this.patientRepository = patientRepository;
        this.encounterRepository = encounterRepository;
        this.supervisorService = supervisorService;
        this.fieldWorkerService = fieldWorkerService;
        this.doctorService = doctorService;
        this.validationHelper = validationHelper;
    }

    public List<FollowUp> getCurrentDateFollowUps(String date, int fieldWorkerAuthId){

        validationHelper.dateValidation(date);

        FieldWorker fieldWorker;

        fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerAuthId);

        List<Patient> patientList = fieldWorker.getPatientList();

        List<FollowUp> followUpList= new ArrayList<FollowUp>();

//        patientList.forEach(patient -> followUpRepository.findByDateAndPatient(date, patient).forEach(followUp -> followUpList.add(followUp)));

        patientList.forEach(patient -> {followUpList.addAll(followUpRepository.findByDateAndPatient(date, patient));});

        return followUpList;
    }


    public FollowUp updateFollowUp(FollowUp followUp) throws RuntimeException{

        Optional<FollowUp> updatedFollowUp = followUpRepository.findById(followUp.getFollowUpId());

        if(updatedFollowUp.isEmpty())
        {
            throw new ResourceNotFoundException("Follow up with id: "+ followUp.getFollowUpId()+ " not found");
        }

        updatedFollowUp.get().setFlag(followUp.isFlag());
        updatedFollowUp.get().setLastSyncDate(followUp.getLastSyncDate());
        updatedFollowUp.get().setFieldWorkerRemarks(followUp.getFieldWorkerRemarks());
        updatedFollowUp.get().setReadings(followUp.getReadings());

        followUpRepository.save(updatedFollowUp.get());
        return updatedFollowUp.get();
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
}
