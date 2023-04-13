package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperAdminService {
    AdminRepository adminRepository;
    SupervisorRepository supervisorRepository;

    public SuperAdminService(AdminRepository adminRepository, SupervisorRepository supervisorRepository) {
        this.adminRepository = adminRepository;
        this.supervisorRepository = supervisorRepository;
    }

    public List<Object> getAllUsers(){
        List<Admin> adminList= adminRepository.findAll();
        List<Supervisor> supervisorList= supervisorRepository.findAll();

        List<Object> userList = new ArrayList<>();
        userList.addAll(adminList);
        userList.addAll(supervisorList);

        return userList;
    }

}
