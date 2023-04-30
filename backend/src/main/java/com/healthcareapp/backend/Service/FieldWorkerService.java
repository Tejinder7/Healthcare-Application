package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class FieldWorkerService {
    private FieldWorkerRepository fieldWorkerRepository;
    private SupervisorService supervisorService;
    private PatientService patientService;
    private AuthorizationService authorizationService;
    private PasswordEncoder passwordEncoder;
    private ValidationHelper validationHelper;

    public FieldWorkerService(FieldWorkerRepository fieldWorkerRepository, SupervisorService supervisorService, PatientService patientService, AuthorizationService authorizationService, PasswordEncoder passwordEncoder, ValidationHelper validationHelper) {
        this.fieldWorkerRepository = fieldWorkerRepository;
        this.supervisorService = supervisorService;
        this.patientService = patientService;
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
        this.validationHelper = validationHelper;
    }

    public FieldWorker addFieldWorker(FieldWorker fieldWorker, String supervisorUserId) throws RuntimeException{
        authorizationService.checkIfUserIdExists(fieldWorker.getUsername());

        validationHelper.usernamePasswordValidation(fieldWorker.getUsername());
        validationHelper.usernamePasswordValidation(fieldWorker.getPassword());
        validationHelper.nameValidation(fieldWorker.getName());
        validationHelper.strValidation(fieldWorker.getAddress());
        validationHelper.pincodeValidation(fieldWorker.getPincode());
        validationHelper.contactValidation(fieldWorker.getContact());

        FieldWorker savedFieldWorker;

        Supervisor supervisor = supervisorService.getSupervisorByUserId(supervisorUserId);

        fieldWorker.setPassword(passwordEncoder.encode(fieldWorker.getPassword()));
        fieldWorker.setSupervisor(supervisor);
        fieldWorker.setRole(Role.ROLE_FIELD_WORKER);
        fieldWorker.setAvailableStatus(true);

        String date = java.time.LocalDate.now().toString();
        fieldWorker.setLastSyncDate(date);

        savedFieldWorker = fieldWorkerRepository.save(fieldWorker);

        return savedFieldWorker;
    }

    public List<FieldWorker> getFieldWorkers(String supervisorUserId){

        validationHelper.usernamePasswordValidation(supervisorUserId);
        Supervisor supervisor = supervisorService.getSupervisorByUserId(supervisorUserId);

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findBySupervisor(supervisor);

        if(fieldWorkerList.size()==0)
        {
            return new ArrayList<FieldWorker>();
        }
        else
            return fieldWorkerList;
    }

    public List<FieldWorker> getAvailableFieldWorkers(String supervisorUserId){
        validationHelper.usernamePasswordValidation(supervisorUserId);
        Supervisor supervisor = supervisorService.getSupervisorByUserId(supervisorUserId);

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findBySupervisorAndAvailableStatusIsTrue(supervisor);

        if(fieldWorkerList.size()==0)
        {
            return new ArrayList<FieldWorker>();
        }
        else
            return fieldWorkerList;
    }

    public FieldWorker assignFollowUp(int patientId, int fieldWorkerAuthId) throws RuntimeException{

        Patient patient = patientService.getPatientById(patientId);

        FieldWorker fieldWorker = this.getFieldWorkerById(fieldWorkerAuthId);

        patient.setFieldWorker(fieldWorker);

        patientService.updatePatient(patient);

        return fieldWorker;
    }

    public FieldWorker getFieldWorkerById(int fieldWorkerId){
        Optional<FieldWorker> fieldWorker = fieldWorkerRepository.findById(fieldWorkerId);
        if(fieldWorker.isEmpty()){
            throw new RuntimeException("Field Worker with id: "+ fieldWorkerId+ " not found");
        }

        return fieldWorker.get();
    }

    public FieldWorker updateFieldWorker(FieldWorker fieldWorker) throws RuntimeException{

        validationHelper.usernamePasswordValidation(fieldWorker.getUsername());
        validationHelper.usernamePasswordValidation(fieldWorker.getPassword());
        validationHelper.nameValidation(fieldWorker.getName());
        validationHelper.contactValidation(fieldWorker.getContact());

        FieldWorker fieldWorkerFromdb = fieldWorkerRepository.findById(fieldWorker.getAuthId()).orElseThrow();

        if(!Objects.equals(fieldWorkerFromdb.getUsername(), fieldWorker.getUsername())){
            authorizationService.checkIfUserIdExists(fieldWorker.getUsername());
        }

        fieldWorkerFromdb.setUsername(fieldWorker.getUsername());
        fieldWorkerFromdb.setName(fieldWorker.getName());
        fieldWorkerFromdb.setContact(fieldWorker.getContact());
        fieldWorkerFromdb.setLastSyncDate(fieldWorker.getLastSyncDate());
        if(!Objects.equals(fieldWorker.getPassword(), "")){
            fieldWorkerFromdb.setPassword(passwordEncoder.encode(fieldWorker.getPassword()));
        }
        FieldWorker updatedFieldWorker =fieldWorkerRepository.save(fieldWorkerFromdb);

        return updatedFieldWorker;
    }

    public FieldWorker getFieldWorkerByUsername(String username){
        validationHelper.usernamePasswordValidation(username);
        FieldWorker fieldWorker = fieldWorkerRepository.findByUsername(username);
        if(fieldWorker == null){
            throw new RuntimeException("Field Worker with id: "+ username+ " not found");
        }
        return fieldWorker;
    }

}
