package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
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
}
