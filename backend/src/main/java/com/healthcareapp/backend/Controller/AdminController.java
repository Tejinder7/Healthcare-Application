package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.AdminService;
import com.healthcareapp.backend.Service.DoctorService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AdminController {
    private DoctorService doctorService;
    private AdminService adminService;

    public AdminController(DoctorService doctorService) {
        this.doctorService = doctorService;
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
