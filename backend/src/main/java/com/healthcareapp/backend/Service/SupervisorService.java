package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class SupervisorService {
    SupervisorRepository supervisorRepository;

    HospitalService hospitalService;

    public SupervisorService(SupervisorRepository supervisorRepository, HospitalService hospitalService) {
        this.supervisorRepository = supervisorRepository;
        this.hospitalService = hospitalService;
    }

    public Supervisor addSupervisor(Supervisor supervisor) throws RuntimeException{
        supervisor.setUserType("Supervisor");
        supervisorRepository.save(supervisor);
        Optional<List<Hospital>> hospitalList = hospitalService.getHospitalsWithPincode(supervisor);
        return supervisor;
    }

    public Supervisor updateSupervisor(Supervisor supervisor) throws RuntimeException{
        supervisorRepository.save(supervisor);
        return supervisor;
    }


    public List<Patient> unAssignedPatients(String userId){
        Optional<Supervisor> supervisor = supervisorRepository.findSupervisorByUserId(userId);

        if(supervisor.isEmpty())
        {
            throw new ResourceNotFoundException("No Supervisor with id: "+ userId);
        }

        List<Hospital> hospitalList = supervisor.get().getHospitalList();

        if(hospitalList.isEmpty()){
            throw new ResourceNotFoundException("No hospitals registered under supervisor with id: "+ userId);
        }

        List<FollowUp> followUpList = new ArrayList<>();

        hospitalList.forEach(hospital -> {followUpList.addAll(hospital.getFollowUpList());});

        HashSet<Patient> patientList = new HashSet<>();

        followUpList.forEach(followUp -> {patientList.add(followUp.getPatient());});

        List<Patient> unAssignedPatients = new ArrayList<>();
        
        patientList.forEach(patient -> {if(patient.getFieldWorker()==null) unAssignedPatients.add(patient);});
        
        return unAssignedPatients;
    }

    public Optional<Supervisor> getSupervisorByPincode(int pincode){
        Optional<Supervisor> supervisor = supervisorRepository.findByPincode(pincode);

//        if(supervisor.isEmpty()){
//            throw new ResourceNotFoundException("No supervisor for the address: "+ pincode+ " found");
//        }

        return supervisor;
    }

    public List<Supervisor> getListOfSupervisors(){
        List<Supervisor> supervisorList= supervisorRepository.findAll();

        return supervisorList;
    }
}

