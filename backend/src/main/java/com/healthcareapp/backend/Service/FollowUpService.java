package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FollowUpService {
    private FollowUpRepository followUpRepository;
    private FieldWorkerRepository fieldWorkerRepository;
    private SupervisorRepository supervisorRepository;
    private EncounterService encounterService;
    private FieldWorkerService fieldWorkerService;
    private PatientService patientService;

    private DoctorService doctorService;

    public FollowUpService(FollowUpRepository followUpRepository, FieldWorkerRepository fieldWorkerRepository, SupervisorRepository supervisorRepository, EncounterService encounterService, FieldWorkerService fieldWorkerService, PatientService patientService, DoctorService doctorService) {
        this.followUpRepository = followUpRepository;
        this.fieldWorkerRepository = fieldWorkerRepository;
        this.supervisorRepository = supervisorRepository;
        this.encounterService = encounterService;
        this.fieldWorkerService = fieldWorkerService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public List<FollowUp> getCurrentDateFollowUps(String date, int fieldWorkerAuthId){
        FieldWorker fieldWorker;

        fieldWorker = fieldWorkerRepository.findFieldWorkerByAuthId(fieldWorkerAuthId);

        if(fieldWorker==null)
        {
            throw new RuntimeException();
        }

        List<Patient> patientList = fieldWorker.getPatientList();

        List<FollowUp> followUpList = new ArrayList<>();

        patientList.forEach(patient -> {followUpList.addAll(followUpRepository.findByDateAndPatient(date, patient));});

        return followUpList;
    }


    public FollowUp updateFollowUp(FollowUp followUp){

        int followUpId = followUp.getFollowUpId();

        FollowUp validFollowUp = followUpRepository.findById(followUpId);

        if(validFollowUp==null)
        {
            throw new RuntimeException();
        }

        validFollowUp.setFlag(followUp.isFlag());
        validFollowUp.setLastSyncDate(followUp.getLastSyncDate());
        validFollowUp.setRemarks(followUp.getRemarks());

        try {
            validFollowUp = followUpRepository.save(validFollowUp);
        }catch (Exception e){
            throw new RuntimeException();
        }

        return validFollowUp;
    }

    public List<FollowUp> getAllFollowUp(int authId){
        Supervisor supervisor = supervisorRepository.findSupervisorByAuthId(authId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = supervisor.getHospitalList();

        //System.out.printf(hospitalList.toString());

        hospitalList.forEach(hospital -> {followUpList.addAll(followUpRepository.findByHospitalAndFlagIsFalse(hospital));});

        return followUpList;
    }

    public List<FollowUp> addFollowUps(List<String> dateList, int en_id){
        Encounter encounter = encounterService.getEncounterById(en_id);
        Patient patient = encounter.getPatient();
        Doctor doctor = encounter.getDoctor();
        Hospital hospital = doctor.getHospital();
        List<FollowUp> followUpList = new ArrayList<>();
        try {
            for (int i = 0; i < dateList.size(); i++) {
                FollowUp followUp = new FollowUp();
                followUp.setEncounter(encounter);
                followUp.setDate(dateList.get(i));
                followUp.setFlag(false);
                followUp.setPatient(patient);
                followUp.setHospital(hospital);
                followUpRepository.save(followUp);

                followUpList.add(followUp);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return followUpList;
    }

    public List<FollowUp> getFollowUpsByFieldWorker(int fieldWorkerId){

        FieldWorker fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerId);
        List<Patient> patientList = patientService.getPatientByFieldWorker(fieldWorker);
        List<FollowUp> followUpList = new ArrayList<>();
        for(int i=0; i<patientList.size(); i++){
            List<FollowUp> followUpListForPatient = followUpRepository.findByPatient(patientList.get(i));
            followUpList.addAll(followUpListForPatient);
        }
        return followUpList;
    }

    public List<FollowUp> getAllFollowUpsAssignedByDoctor(String doctorUserId) throws RuntimeException{
        Doctor doctor= doctorService.getDoctorByUserId(doctorUserId);

        List<Encounter> encounterList= encounterService.getEncounterByDoctor(doctor);

        List<FollowUp> followUpList= new ArrayList<>();

        encounterList.forEach(encounter -> followUpList.addAll(encounter.getFollowUpList()));

        if(followUpList.isEmpty()){
            throw new ResourceNotFoundException("No Follow ups assigned to any patient till now");
        }

        return followUpList;
    }
}
