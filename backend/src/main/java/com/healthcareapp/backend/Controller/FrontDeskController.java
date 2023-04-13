package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Service.FrontDeskService;
import com.healthcareapp.backend.Service.PatientService;
import com.healthcareapp.backend.Service.PendingQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/frontdesk")
public class FrontDeskController {
    private FrontDeskService frontDeskService;
    private PatientService patientService;

    private PendingQueueService pendingQueueService;

    public FrontDeskController(FrontDeskService frontDeskService, PatientService patientService, PendingQueueService pendingQueueService) {
        this.frontDeskService = frontDeskService;
        this.patientService = patientService;
        this.pendingQueueService = pendingQueueService;
    }

    @PostMapping("/addPatients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) throws RuntimeException {
        Patient savedPatient;
        try{
            savedPatient = patientService.addPatient(patient);
        }catch(RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedPatient));
    }

    @GetMapping("/getPatientById/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
        Patient patient;
        try {
            patient = patientService.getPatientById(patientId);
        }
        catch (Exception exception) {
            throw exception;
        }
        return ResponseEntity.of(Optional.of(patient));
    }

    @GetMapping("/getPatientsByName/{patientName}")
    public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable String patientName){
        List<Patient> patientList;
        try{
            patientList = patientService.getPatientsByName(patientName);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(patientList));
    }

    @PostMapping("/addPendingQueue/{frontdeskUserId}/{pid}")
    public ResponseEntity<PendingQueue> addToPendingQueue(@PathVariable String frontdeskUserId, @PathVariable int pid){

        PendingQueue pendingQueue;
        try {
            pendingQueue = this.pendingQueueService.addPendingQueue(frontdeskUserId, pid);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(pendingQueue));
    }


}
