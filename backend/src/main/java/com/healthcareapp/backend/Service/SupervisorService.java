package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;

import com.healthcareapp.backend.Model.Admin;

import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupervisorService {
    SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public Supervisor addSupervisor(Supervisor supervisor){
        try{
            supervisor.setUserType("Supervisor");
            supervisorRepository.save(supervisor);
            return supervisor;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Supervisor updateSupervisor(Supervisor supervisor){
        try {
            supervisorRepository.save(supervisor);
            return supervisor;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public List<Patient> unAssignedPatients(int supId){
        Supervisor supervisor = supervisorRepository.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<Hospital> hospitalList = supervisor.getHospitalList();

        List<FollowUp> followUpList = new ArrayList<>();

        hospitalList.forEach(hospital -> {followUpList.addAll(hospital.getFollowUpList());});

        List<Patient> patientList = new ArrayList<>();

        followUpList.forEach(followUp -> {if(followUp.getPatientId()==null) patientList.add(followUp.getPatientId());});

        return patientList;
    }
}

