package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addPatients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient savedPatient;
        try {
            savedPatient = patientService.addPatient(patient);
        }
        catch (Exception exception){
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


}
