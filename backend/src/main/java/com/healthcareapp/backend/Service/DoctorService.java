package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;
    private AuthorizationService authorizationService;
    private AdminService adminService;
    private ValidationHelper validationHelper;
    private PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, AuthorizationService authorizationService, AdminService adminService, PasswordEncoder passwordEncoder, ValidationHelper validationHelper) {
        this.doctorRepository = doctorRepository;
        this.authorizationService = authorizationService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
        this.validationHelper = validationHelper;
    }

    public Doctor addDoctor(Doctor doctor, String userId) throws RuntimeException{
        authorizationService.checkIfUserIdExists(doctor.getUsername());

        validationHelper.usernamePasswordValidation(doctor.getUsername());
        validationHelper.usernamePasswordValidation(doctor.getPassword());
        validationHelper.nameValidation(doctor.getName());
        validationHelper.contactValidation(doctor.getContact());
        validationHelper.strValidation(doctor.getDocSpecialization());
        validationHelper.usernamePasswordValidation(doctor.getLicId());

        Admin admin =  adminService.getAdminByUserId(userId);

        doctor.setHospital(admin.getHospital());

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));

        if(doctor.getDocSpecialization() == null){
            doctor.setDocSpecialization("General");
        }
        
        doctor.setRole(Role.ROLE_DOCTOR);

        Doctor savedDoctor= doctorRepository.save(doctor);
        return savedDoctor;
    }

    public Doctor updateDoctor(Doctor doctor) throws RuntimeException{

        validationHelper.usernamePasswordValidation(doctor.getUsername());
        validationHelper.usernamePasswordValidation(doctor.getPassword());
        validationHelper.nameValidation(doctor.getName());
        validationHelper.contactValidation(doctor.getContact());
        validationHelper.strValidation(doctor.getDocSpecialization());
        validationHelper.usernamePasswordValidation(doctor.getLicId());

        Optional<Doctor> updatedDoctor = doctorRepository.findById(doctor.getAuthId());

        if(!Objects.equals(updatedDoctor.get().getUsername(), doctor.getUsername())){
            authorizationService.checkIfUserIdExists(doctor.getUsername());
        }

        if(updatedDoctor.isEmpty()){
            throw new ResourceNotFoundException("No Doctor with id: "+ doctor.getAuthId()+ " found");
        }

        updatedDoctor.get().setDocSpecialization(doctor.getDocSpecialization());
        updatedDoctor.get().setName(doctor.getName());
        updatedDoctor.get().setLicId(doctor.getLicId());
        updatedDoctor.get().setContact(doctor.getContact());
        updatedDoctor.get().setUsername(doctor.getUsername());
        if(!Objects.equals(doctor.getPassword(), ""))
            updatedDoctor.get().setPassword(passwordEncoder.encode(doctor.getPassword()));

        Doctor doctor1 = doctorRepository.save(updatedDoctor.get());
        return doctor1;
    }

    public Doctor getDoctorByUserId(String doctorUserId){
        Optional<Doctor> doctor= doctorRepository.findByUsername(doctorUserId);

        if(doctor.isEmpty()){
            throw new ResourceNotFoundException("Doctor with userID: "+ doctorUserId+" not found");
        }

        return doctor.get();
    }
}
