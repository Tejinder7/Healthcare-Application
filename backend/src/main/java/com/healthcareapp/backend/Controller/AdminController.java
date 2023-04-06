package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.AdminService;
import com.healthcareapp.backend.Service.HospitalService;
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
        Admin admin1;
        try{
            admin1 = adminService.addAdmin(admin, hospId);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.of(Optional.of(admin1));
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        Admin admin1;
        try{
            admin1 = adminService.updateAdmin(admin);
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.of(Optional.of(admin1));
    }

    @GetMapping("/getAllHospitalUsers/{hospitalId}")
    public List<Object> getAllHospitalUser(@PathVariable int hospitalId){
        try {
            List<Object> userList = adminService.getAllHospitalUsers(hospitalId);
            return userList;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
