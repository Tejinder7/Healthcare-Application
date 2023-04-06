package com.healthcareapp.backend.Service;

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

    public Admin addAdmin(Admin admin, int hospId){
//        if(adminRepository.findAdminByAuthId(admin.getAuthId())!= null){
//            //Admin already exists
//            throw new RuntimeException();
//        }

        Hospital hospital= hospitalService.getHospitalById(hospId);
        admin.setHospital(hospital);
        admin.setUserType("Admin");

        try{
            adminRepository.save(admin);
            return admin;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Admin updateAdmin(Admin admin){
        Hospital hospital = hospitalService.getHospitalById(admin.getHospital().getHospId());
        admin.setHospital(hospital);
        try {
            adminRepository.save(admin);
            return admin;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public List<Object> getAllHospitalUsers(int hospitalId){
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital = hospitalService.getHospitalById(hospitalId);

        try {
            doctorList = doctorService.getAllDoctorsByHospital(hospital);
            frontDeskList = frontDeskService.getAllFrontDeskByHospital(hospital);
        }catch (Exception e){
            throw new RuntimeException();
        }

        List<Object> userList = new ArrayList<>();
        userList.addAll(doctorList);
        userList.addAll(frontDeskList);

        return userList;
    }
}
