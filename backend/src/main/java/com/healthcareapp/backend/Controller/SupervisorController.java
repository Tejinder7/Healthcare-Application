package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Supervisor supervisor1;
        try{
            supervisor1 = supervisorService.addSupervisor(supervisor);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.of(Optional.of(supervisor1));
    }

    @PutMapping("/updateSupervisor")
    public ResponseEntity<Supervisor> updateSupervisor(@RequestBody Supervisor supervisor){
        Supervisor supervisor1;
        try{
            supervisor1 = supervisorService.updateSupervisor(supervisor);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.of(Optional.of(supervisor1));
    }
}
