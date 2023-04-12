package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminService {
    AdminRepository adminRepository;
    AuthorizationRepository authorizationRepository;
    HospitalService hospitalService;
    DoctorService doctorService;
    FrontDeskService frontDeskService;

//    AuthorizationService authorizationService;

    public AdminService(AdminRepository adminRepository, HospitalService hospitalService, DoctorService doctorService, FrontDeskService frontDeskService, AuthorizationRepository authorizationRepository) {
        this.adminRepository = adminRepository;
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
        this.frontDeskService = frontDeskService;
        this.authorizationRepository = authorizationRepository;
    }

    public Admin addAdmin(Admin admin, int hospId) throws RuntimeException{
        Optional<Authorization> user= authorizationRepository.findByUserId(admin.getUserId());

        if(user.isPresent()){
            throw new ForbiddenException("User already exists. Please try again with a different userId");
        }

        Hospital hospital= hospitalService.getHospitalById(hospId);

        admin.setHospital(hospital);
        admin.setUserType("Admin");

        Admin savedAdmin= adminRepository.save(admin);

        return savedAdmin;
    }

    public Admin updateAdmin(Admin admin) throws RuntimeException{
        Admin updatedAdmin= adminRepository.save(admin);

        return updatedAdmin;
    }

    public List<Object> getAllHospitalUsers(String userId) throws RuntimeException{
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital;

        Optional<Admin> admin = adminRepository.findByUserId(userId);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Admin with userId: "+ userId+ " not found");
        }

        hospital = hospitalService.getHospitalById(admin.get().getHospital().getHospId());

        doctorList = doctorService.getAllDoctorsByHospital(hospital);
        frontDeskList = frontDeskService.getAllFrontDeskByHospital(hospital);

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

//    public Admin getAdminByUserId(String userId){
//        Admin admin = adminRepository.findAdminByUserId(userId);
//        return admin;
//    }
}
