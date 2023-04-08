package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
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

        hospital= hospitalService.getHospitalById(hospId);

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

    public List<Object> getAllHospitalUsers(int hospitalId) throws RuntimeException{
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital;

        hospital = hospitalService.getHospitalById(hospitalId);

        doctorList = doctorService.getAllDoctorsByHospital(hospital);
        frontDeskList = frontDeskService.getAllFrontDeskByHospital(hospital);

        List<Object> userList = new ArrayList<>();

        userList.addAll(doctorList);
        userList.addAll(frontDeskList);

        if(userList.isEmpty()){
            throw new ResourceNotFoundException("No Doctor or Front Desk registered under hospital with id: "+ hospitalId);
        }

        return userList;
    }

    public List<Admin> getListOfAdmins(){
        List<Admin> adminList= adminRepository.findAll();

        return adminList;
    }


    public List<Hospital> getHospitalsWhereAdminNotAssigned(){
        List<Hospital> hospitalList = hospitalService.getAllHospitals();
        List<Admin> adminList = adminRepository.findAll();

        List<Hospital> hospitalAssignedList = new ArrayList<>();

        adminList.forEach(admin -> {hospitalAssignedList.add(admin.getHospital());});

        List<Hospital> hospitalNotAssignedList = new ArrayList<>(hospitalList);
        hospitalNotAssignedList.removeAll(hospitalAssignedList);

        return hospitalNotAssignedList;
    }
}
