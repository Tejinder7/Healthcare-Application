package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SupervisorService {
    private SupervisorRepository supervisorRepository;

    private HospitalService hospitalService;

    private AuthorizationService authorizationService;

    private PasswordEncoder passwordEncoder;

    private ValidationHelper validationHelper;

    public SupervisorService(SupervisorRepository supervisorRepository, HospitalService hospitalService, AuthorizationService authorizationService, PasswordEncoder passwordEncoder, ValidationHelper validationHelper) {
        this.supervisorRepository = supervisorRepository;
        this.hospitalService = hospitalService;
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
        this.validationHelper = validationHelper;
    }

    public Supervisor addSupervisor(Supervisor supervisor) throws RuntimeException{
        authorizationService.checkIfUserIdExists(supervisor.getUsername());

        validationHelper.usernamePasswordValidation(supervisor.getUsername());
        validationHelper.usernamePasswordValidation(supervisor.getPassword());
        validationHelper.strValidation(supervisor.getAddress());
        validationHelper.pincodeValidation(supervisor.getPincode());
        validationHelper.nameValidation(supervisor.getName());
        validationHelper.contactValidation(supervisor.getContact());

        if(supervisorRepository.findByPincode(supervisor.getPincode()).isPresent()){
            throw new ForbiddenException("Supervisor already exists with the given pincode. Please try again with a different pincode");
        }

        supervisor.setRole(Role.ROLE_SUPERVISOR);
        supervisor.setPassword(passwordEncoder.encode(supervisor.getPassword()));
        Supervisor savedSupervisor = supervisorRepository.save(supervisor);

        hospitalService.setSupervisorByPincode(savedSupervisor);

        return savedSupervisor;
    }

    public Supervisor updateSupervisor(Supervisor supervisor) throws RuntimeException{

        validationHelper.usernamePasswordValidation(supervisor.getUsername());
        validationHelper.usernamePasswordValidation(supervisor.getPassword());
        validationHelper.nameValidation(supervisor.getName());
        validationHelper.contactValidation(supervisor.getContact());

        Supervisor supervisorFromDb = supervisorRepository.findById(supervisor.getAuthId()).orElseThrow();

        if(!Objects.equals(supervisorFromDb.getUsername(), supervisor.getUsername())){
            authorizationService.checkIfUserIdExists(supervisor.getUsername());
        }

        supervisorFromDb.setUsername(supervisor.getUsername());
        supervisorFromDb.setName(supervisor.getName());
        supervisorFromDb.setContact(supervisor.getContact());
        if(!Objects.equals(supervisor.getPassword(), "")){
            supervisorFromDb.setPassword(passwordEncoder.encode(supervisor.getPassword()));
        }
        Supervisor updatedSupervisor =supervisorRepository.save(supervisorFromDb);

        return updatedSupervisor;
    }


    public List<Patient> unAssignedPatients(String userId){
        validationHelper.usernamePasswordValidation(userId);
        Optional<Supervisor> supervisor = supervisorRepository.findByUsername(userId);

        if(supervisor.isEmpty())
        {
            throw new ResourceNotFoundException("Supervisor with id: "+ userId+ " not found");
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

    public Supervisor getSupervisorByUserId(String userId){
        validationHelper.usernamePasswordValidation(userId);
        Optional<Supervisor> supervisor= supervisorRepository.findByUsername(userId);

        if(supervisor.isEmpty()){
            throw new ResourceNotFoundException("Supervisor with userId: "+ userId+ " not found");
        }

        return supervisor.get();
    }
}

