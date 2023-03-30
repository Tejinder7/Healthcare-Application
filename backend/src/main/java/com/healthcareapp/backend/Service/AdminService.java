package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.AdminRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
    AdminRepository adminRepository;
    HospitalService hospitalService;

    public AdminService(AdminRepository adminRepository, HospitalService hospitalService) {
        this.adminRepository = adminRepository;
        this.hospitalService = hospitalService;
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
}
