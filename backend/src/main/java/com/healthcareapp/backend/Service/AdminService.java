package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.AdminRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminService {
    AdminRepository adminRepository;
    HospitalService hospitalService;

    DoctorService doctorService;

    FrontDeskService frontDeskService;

    public AdminService(AdminRepository adminRepository, HospitalService hospitalService, DoctorService doctorService, FrontDeskService frontDeskService) {
        this.adminRepository = adminRepository;
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
        this.frontDeskService = frontDeskService;
    }

    public Admin addAdmin(Admin admin, int hospId) throws RuntimeException{
        Hospital hospital;

        try{
            hospital= hospitalService.getHospitalById(hospId);
        }
        catch (RuntimeException exception){
            throw exception;
        }

        admin.setHospital(hospital);
        admin.setUserType("Admin");

        Admin savedAdmin;

        savedAdmin= adminRepository.save(admin);

        return savedAdmin;
    }

    public Admin updateAdmin(Admin admin) throws RuntimeException{
        Admin updatedAdmin;

        updatedAdmin= adminRepository.save(admin);

        return updatedAdmin;
    }

    public List<Object> getAllHospitalUsers(int hospitalId){
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital;

        try{
            hospital = hospitalService.getHospitalById(hospitalId);
        }
        catch (ResourceNotFoundException exception){
            throw exception;
        }
        doctorList = doctorService.getAllDoctorsByHospital(hospital);
        frontDeskList = frontDeskService.getAllFrontDeskByHospital(hospital);

        List<Object> userList = new ArrayList<>();

        userList.addAll(doctorList);
        userList.addAll(frontDeskList);

        return userList;
    }
}
