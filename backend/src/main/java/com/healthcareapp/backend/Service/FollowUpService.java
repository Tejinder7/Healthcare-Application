package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import com.healthcareapp.backend.Model.*;
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

        try {
            followUp = followUpRepository.save(followUp);
        }catch (Exception e){
            throw new RuntimeException();
        }

        return followUp;
    }

    public List<FollowUp> getAllFollowUp(int supId){
        Supervisor sup = supervisorRepository.findSupervisorBySupId(supId);

        if(sup==null)
        {
            throw new RuntimeException();
        }

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = sup.getHospitalList();

        hospitalList.forEach(hospital -> {followUpList.add(followUpRepository.findByHospId(hospital));});

        return followUpList;
    }

    public List<FollowUp> addFollowUps(List<String> dateList, int en_id){
        Encounter encounter = encounterService.getEncounterById(en_id);
        Patient patient = encounter.getPatientId();
        Doctor doctor = encounter.getDoctorId();
        Hospital hospital = doctor.getHospId();
        List<FollowUp> fuList = new ArrayList<>();
        try {
            for (int i = 0; i < dateList.size(); i++) {
                FollowUp fu = new FollowUp();
                fu.setEncounterId(encounter);
                fu.setDate(dateList.get(i));
                fu.setFlag(false);
                fu.setPatientId(patient);
                fu.setHospId(hospital);
                followUpRepository.save(fu);

                fuList.add(fu);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fuList;
    }
}
