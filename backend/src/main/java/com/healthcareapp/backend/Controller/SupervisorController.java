package com.healthcareapp.backend.Controller;


import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SupervisorController {
    private SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService) {
        this.supervisorService = supervisorService;
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

    @GetMapping("/unassignedPatients/{supId}")
    public ResponseEntity<List<Patient>> unassignedPatients(@PathVariable int supervisorId){
        List<Patient> patientList;

        try{
            patientList = supervisorService.unAssignedPatients(supervisorId);
        }catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(patientList));
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
