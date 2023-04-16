package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.SuperAdminRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperAdminService {
    AdminRepository adminRepository;
    SupervisorRepository supervisorRepository;

    SuperAdminRepository superAdminRepository;

    AuthorizationService authorizationService;

    PasswordEncoder passwordEncoder;

    public SuperAdminService(AdminRepository adminRepository, SupervisorRepository supervisorRepository, SuperAdminRepository superAdminRepository, AuthorizationService authorizationService, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.supervisorRepository = supervisorRepository;
        this.superAdminRepository = superAdminRepository;
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Object> getAllUsers(){
        List<Admin> adminList= adminRepository.findAll();
        List<Supervisor> supervisorList= supervisorRepository.findAll();

        List<Object> userList = new ArrayList<>();
        userList.addAll(adminList);
        userList.addAll(supervisorList);

        return userList;
    }

    public void addSuperAdmin(SuperAdmin superAdmin){
        authorizationService.checkIfUserIdExists(superAdmin.getUsername());
        superAdmin.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
        superAdmin.setRole(Role.ROLE_SUPER_ADMIN);
        superAdminRepository.save(superAdmin);
    }

}
