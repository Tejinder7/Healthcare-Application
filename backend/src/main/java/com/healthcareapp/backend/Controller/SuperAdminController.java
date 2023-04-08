package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SuperAdminController {
    private SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping("/getAllUsers")
    public List<Object> getAllUser(){
        try {
            List<Object> userList = superAdminService.getAllUsers();
            return userList;
        }catch (Exception exception){
            throw exception;
        }
    }

}
