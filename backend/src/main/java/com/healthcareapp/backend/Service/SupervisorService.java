package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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

    public List<Patient> unAssignedPatients(String userId){
        Supervisor supervisor = supervisorRepository.findSupervisorByUserId(userId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<Hospital> hospitalList = supervisor.getHospitalList();

        List<FollowUp> followUpList = new ArrayList<>();

        hospitalList.forEach(hospital -> {followUpList.addAll(hospital.getFollowUpList());});

        HashSet<Patient> patientList = new HashSet<>();

        followUpList.forEach(followUp -> {patientList.add(followUp.getPatient());});

        List<Patient> unAssignedPatients = new ArrayList<>();
        
        patientList.forEach(patient -> {if(patient.getFieldWorker()==null) unAssignedPatients.add(patient);});
        
        return unAssignedPatients;
    }

    public Supervisor getSupervisorByAddress(String Address){
        Optional<Supervisor> supervisor = supervisorRepository.findByAddress(Address);

        if(supervisor.isEmpty()){
            throw new ResourceNotFoundException("No supervisor for the address: "+ Address+ " found");
        }

        return supervisor.get();
    }
}

