package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping("/getAllHospitalUsers/{userId}")
    public List<Object> getAllHospitalUsers(@PathVariable String userId){
        List<Object> hospitalUsersList;
        try {
            hospitalUsersList = adminService.getAllHospitalUsers(userId);
        }
        catch (Exception exception){
            throw exception;
        }
        return hospitalUsersList;
    }

    @GetMapping("/hospitalsWithNoAdmins")
    public List<Hospital> getHospitalsWithNoAdmins(){
        List<Hospital> hospitalList;

        hospitalList = adminService.getHospitalsWhereAdminNotAssigned();

        return hospitalList;
    }
}
