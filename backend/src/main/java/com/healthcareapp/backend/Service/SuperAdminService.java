package com.healthcareapp.backend.Service;

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

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    SupervisorRepository supervisorRepository;

    public List<Object> getAllUsers(){
        List<Admin> adminList;
        List<Supervisor> supervisorList;

        try {
            adminList = adminRepository.findAll();
            supervisorList = supervisorRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException();
        }

        List<Object> userList = new ArrayList<>();
        userList.addAll(adminList);
        userList.addAll(supervisorList);

        return userList;
    }

}
