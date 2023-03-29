package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FollowUpService {
    private FollowUpRepository followUpRepository;
    private FieldWorkerRepository fieldWorkerRepository;
    private SupervisorRepository supervisorRepository;
    private EncounterService encounterService;

    public FollowUpService(FollowUpRepository followUpRepository, FieldWorkerRepository fieldWorkerRepository, SupervisorRepository supervisorRepository, EncounterService encounterService) {
        this.followUpRepository = followUpRepository;
        this.fieldWorkerRepository = fieldWorkerRepository;
        this.supervisorRepository = supervisorRepository;
        this.encounterService = encounterService;
    }

    public List<FollowUp> getCurrentDateFollowUps(String date, int fwId){
        FieldWorker fieldWorker;

        fieldWorker = fieldWorkerRepository.findByFwId(fwId);

        if(fieldWorker==null)
        {
            throw new RuntimeException();
        }

        List<Patient> patientList = fieldWorker.getPatientList();

        List<FollowUp> followUpList = new ArrayList<>();

        patientList.forEach(patient -> {followUpList.addAll(followUpRepository.findByDateAndPatientId(date, patient));});

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

    public List<FollowUp> getAllFollowUp(int supId){
        Supervisor supervisor = supervisorRepository.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = supervisor.getHospitalList();

        //System.out.printf(hospitalList.toString());

        hospitalList.forEach(hospital -> {followUpList.addAll(followUpRepository.findByHospId(hospital));});

        return followUpList;
    }

    public List<FollowUp> addFollowUps(List<String> dateList, int en_id){
        Encounter encounter = encounterService.getEncounterById(en_id);
        Patient patient = encounter.getPatientId();
        Doctor doctor = encounter.getDoctorId();
        Hospital hospital = doctor.getHospId();
        List<FollowUp> followUpList = new ArrayList<>();
        try {
            for (int i = 0; i < dateList.size(); i++) {
                FollowUp followUp = new FollowUp();
                followUp.setEncounterId(encounter);
                followUp.setDate(dateList.get(i));
                followUp.setFlag(false);
                followUp.setPatientId(patient);
                followUp.setHospId(hospital);
                followUpRepository.save(followUp);

                followUpList.add(followUp);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return followUpList;
    }
}
