package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Repository.AdminRepository;

public class AdminService {
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin addAdmin(Admin admin){
        Admin admin1= adminRepository.findAdminByAuthId(admin.getAuthId());

        if(admin1!= null){
            //Admin already exists
            throw new RuntimeException();
        }

        try{
            admin1= adminRepository.save(admin);
        }
        catch (Exception e){
            throw new RuntimeException();
        }

        return admin;
    }
}
