package com.healthcareapp.backend.Controller;


import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Service.FieldWorkerService;
import com.healthcareapp.backend.Service.FollowUpService;
import com.healthcareapp.backend.Service.PatientService;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
    private SupervisorService supervisorService;

    private FieldWorkerService fieldWorkerService;

    private FollowUpService followUpService;

    private PatientService patientService;

    public SupervisorController(SupervisorService supervisorService, FieldWorkerService fieldWorkerService, FollowUpService followUpService, PatientService patientService) {
        this.supervisorService = supervisorService;
        this.fieldWorkerService = fieldWorkerService;
        this.followUpService = followUpService;
        this.patientService = patientService;
    }

    @PostMapping("/addFieldWorker/{supervisorUserId}")
    public ResponseEntity<FieldWorker> addFieldWorker(@RequestBody FieldWorker fieldWorker, @PathVariable String supervisorUserId) {

        try {
            fieldWorker = fieldWorkerService.addFieldWorker(fieldWorker, supervisorUserId);
        }catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkers/{supervisorUserId}")
    public ResponseEntity<List<FieldWorker>> getFieldWorkers(@PathVariable String supervisorUserId){
        List<FieldWorker> fieldWorkerList;

        try{
            fieldWorkerList = fieldWorkerService.getFieldWorkers(supervisorUserId);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.ok(fieldWorkerList);
    }
    @PutMapping ("/assignFollowUp/{fieldWorkerId}/{patientId}")
    public ResponseEntity<FieldWorker> assignFollowUp(@PathVariable int fieldWorkerId, @PathVariable int patientId){
        FieldWorker fieldWorker;

        try {
            fieldWorker = fieldWorkerService.assignFollowUp(patientId, fieldWorkerId);
        }catch (Exception exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkerDetail/{fieldWorkerId}")
    public ResponseEntity<FieldWorker> getFieldWorkerDetail(@PathVariable int fieldWorkerId){
        FieldWorker fieldWorker;
        try{
            fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerId);
            return ResponseEntity.of(Optional.of(fieldWorker));
        }catch (Exception exception){
            throw exception;
        }
    }

    @GetMapping("/unassignedPatients/{supervisorUserId}")
    public ResponseEntity<List<Patient>> unassignedPatients(@PathVariable String supervisorUserId){
        List<Patient> patientList;
        try{
            patientList = supervisorService.unAssignedPatients(supervisorUserId);
        }catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(patientList));
    }

    @GetMapping("/getFollowUps/{supervisorUserId}")
    public ResponseEntity<List<FollowUp>> getFollowUps(@PathVariable String supervisorUserId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpService.getAllFollowUpsUnderSupervisor(supervisorUserId);
        }catch (Exception exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(followUpList));
    }

    @GetMapping("/getFollowUpsForFieldWorker/{fieldWorkerId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsForFieldWorker(@PathVariable int fieldWorkerId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpService.getFollowUpsByFieldWorker(fieldWorkerId);
        }catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUpList));
    }

    @GetMapping("/getPatientsByFieldWorker/{fieldWorkerId}")
    public ResponseEntity<List<Patient>> getPatientsByFieldWorker(@PathVariable int fieldWorkerId){
        List<Patient> patientList;
        try{
            patientList = patientService.getPatientByFieldWorker(fieldWorkerId);
        }catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(patientList));
    }
}
