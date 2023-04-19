package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Service.AdminService;
import com.healthcareapp.backend.Service.HospitalService;
import com.healthcareapp.backend.Service.SuperAdminService;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {
    private SuperAdminService superAdminService;
    private SupervisorService supervisorService;
    private AdminService adminService;
    private HospitalService hospitalService;

    public SuperAdminController(SuperAdminService superAdminService, SupervisorService supervisorService, AdminService adminService, HospitalService hospitalService) {
        this.superAdminService = superAdminService;
        this.supervisorService = supervisorService;
        this.adminService = adminService;
        this.hospitalService = hospitalService;
    }

    @PostMapping("/addSupervisor")
    public ResponseEntity<Supervisor> addSupervisor(@RequestBody Supervisor supervisor){
        Supervisor savedSupervisor;
        try{
            savedSupervisor = supervisorService.addSupervisor(supervisor);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(savedSupervisor));
    }

    @PostMapping("/addHospital")
    public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital){
        Hospital savedHospital;
        try{
            savedHospital = hospitalService.addHospital(hospital);
        }catch (RuntimeException exception)
        {
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedHospital));
    }

    @PostMapping("/addAdmin/{hospId}")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin, @PathVariable int hospId){
        Admin savedAdmin;
        try{
            savedAdmin = adminService.addAdmin(admin, hospId);
        }
        catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedAdmin));
    }

    @GetMapping("/hospitalsWithNoAdmins")
    public List<Hospital> getHospitalsWithNoAdmins(){
        List<Hospital> hospitalList;

        hospitalList = adminService.getHospitalsWhereAdminNotAssigned();

        return hospitalList;
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

    @PutMapping("/updateSupervisor")
    public ResponseEntity<Supervisor> updateSupervisor(@RequestBody Supervisor supervisor){
        Supervisor updatedSupervisor;
        try{
            updatedSupervisor = supervisorService.updateSupervisor(supervisor);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(updatedSupervisor));
    }

}
