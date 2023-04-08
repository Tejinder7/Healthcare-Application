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
    AdminService adminService;

    SupervisorService supervisorService;

    public SuperAdminService(AdminService adminService, SupervisorService supervisorService) {
        this.adminService = adminService;
        this.supervisorService = supervisorService;
    }

    public List<Object> getAllUsers(){
        List<Admin> adminList;
        List<Supervisor> supervisorList;

        adminList = adminService.getListOfAdmins();
        supervisorList = supervisorService.getListOfSupervisors();

        List<Object> userList = new ArrayList<>();
        userList.addAll(adminList);
        userList.addAll(supervisorList);

        if(userList.isEmpty()){
            throw new ResourceNotFoundException("No resgistered Admin or Supervisor found");
        }

        return userList;
    }

}
