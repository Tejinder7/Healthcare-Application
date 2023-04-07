package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Service.AdminService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addAdmin/{hospId}")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin, @PathVariable int hospId){
        Admin savedAdmin;
        try{
            savedAdmin = adminService.addAdmin(admin, hospId);
        }
        catch (Exception exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedAdmin));
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        Admin updatedAdmin;

        try{
            updatedAdmin = adminService.updateAdmin(admin);
        }
        catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedAdmin));
    }

    @GetMapping("/getAllHospitalUsers/{hospitalId}")
    public List<Object> getAllHospitalUsers(@PathVariable int hospitalId){
        List<Object> userList;
        try {
            userList = adminService.getAllHospitalUsers(hospitalId);
        }
        catch (Exception exception){
            throw exception;
        }
        return userList;
    }
}
