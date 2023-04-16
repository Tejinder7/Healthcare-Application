package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Service.AdminService;
import com.healthcareapp.backend.Service.DoctorService;
import com.healthcareapp.backend.Service.FrontDeskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    private DoctorService doctorService;

    private FrontDeskService frontDeskService;

    public AdminController(AdminService adminService, DoctorService doctorService, FrontDeskService frontDeskService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.frontDeskService = frontDeskService;
    }

    @PostMapping("/addDoctor/{adminUserId}")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @PathVariable String adminUserId){
        Doctor savedDoctor;

        try{
            savedDoctor = doctorService.addDoctor(doctor, adminUserId);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(savedDoctor));
    }

    @PostMapping("/addFrontDesk/{adminUserId}")
    public ResponseEntity<FrontDesk> addFrontDesk(@RequestBody FrontDesk frontDesk, @PathVariable String adminUserId) {
        FrontDesk savedFrontDesk;
        try {
            savedFrontDesk = frontDeskService.addFrontDesk(frontDesk, adminUserId);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(savedFrontDesk));
    }

    @GetMapping("/getAllHospitalUsers/{adminUserId}")
    public List<Object> getAllHospitalUsers(@PathVariable String adminUserId){
        List<Object> hospitalUsersList;
        try {
            hospitalUsersList = adminService.getAllHospitalUsers(adminUserId);
        }
        catch (Exception exception){
            throw exception;
        }
        return hospitalUsersList;
    }
    @PutMapping("/updateDoctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor){
        Doctor updatedDoctor;

        try{
            updatedDoctor = doctorService.updateDoctor(doctor);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedDoctor));
    }

    @PutMapping("/updateFrontDesk")
    public ResponseEntity<FrontDesk> updateFrontDesk(@RequestBody FrontDesk frontDesk){
        FrontDesk updatedFrontDesk;
        try{
            updatedFrontDesk = frontDeskService.updateFrontDesk(frontDesk);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedFrontDesk));
    }
}
