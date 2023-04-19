package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.FrontDeskRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AdminService {
    AdminRepository adminRepository;
    DoctorRepository doctorRepository;
    FrontDeskRepository frontDeskRepository;
    HospitalService hospitalService;
    AuthorizationService authorizationService;

    PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, DoctorRepository doctorRepository, FrontDeskRepository frontDeskRepository, HospitalService hospitalService, AuthorizationService authorizationService, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.frontDeskRepository = frontDeskRepository;
        this.hospitalService = hospitalService;
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin addAdmin(Admin admin, int hospId) throws RuntimeException{
        authorizationService.checkIfUserIdExists(admin.getUsername());

        Hospital hospital= hospitalService.getHospitalById(hospId);

        admin.setHospital(hospital);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        Admin savedAdmin= adminRepository.save(admin);

        return savedAdmin;
    }

    public Admin updateAdmin(Admin admin) throws RuntimeException{//TAKE NULL FROM PASSWORD IF NOT UPDATED
        Optional<Admin> adminFromDb = adminRepository.findById(admin.getAuthId());
        if(!Objects.equals(adminFromDb.get().getUsername(), admin.getUsername())){
            authorizationService.checkIfUserIdExists(admin.getUsername());
        }
        adminFromDb.get().setUsername(admin.getUsername());
        adminFromDb.get().setName(admin.getName());
        if(!Objects.equals(admin.getPassword(), "")){
            adminFromDb.get().setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        Admin updatedAdmin = adminRepository.save(adminFromDb.get());

        return updatedAdmin;
    }

    public List<Object> getAllHospitalUsers(String userId) throws RuntimeException{
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital;

        Optional<Admin> admin = adminRepository.findByUsername(userId);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Admin with userId: "+ userId+ " not found");
        }

        hospital = hospitalService.getHospitalById(admin.get().getHospital().getHospId());

        doctorList = doctorRepository.findByHospital(hospital);
        frontDeskList = frontDeskRepository.findByHospital(hospital);

        List<Object> userList = new ArrayList<>();

        userList.addAll(doctorList);
        userList.addAll(frontDeskList);

        return userList;
    }

    public List<Admin> getListOfAdmins(){
        List<Admin> adminList= adminRepository.findAll();

        return adminList;
    }


    public List<Hospital> getHospitalsWhereAdminNotAssigned(){
        List<Hospital> hospitalList = hospitalService.getAllHospitals();
        List<Admin> adminList = this.getListOfAdmins();

        List<Hospital> hospitalAssignedList = new ArrayList<>();

        adminList.forEach(admin -> hospitalAssignedList.add(admin.getHospital()));

        List<Hospital> hospitalNotAssignedList = new ArrayList<>(hospitalList);
        hospitalNotAssignedList.removeAll(hospitalAssignedList);

        return hospitalNotAssignedList;
    }

    public Admin getAdminByUserId(String userId){
        Optional<Admin> admin = adminRepository.findByUsername(userId);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Admin with userId: "+ userId+ " not found");
        }

        return admin.get();
    }
}
