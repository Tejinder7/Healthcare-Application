package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addPatients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient patient1 = new Patient();
        try {
            patient1 = this.patientService.addPatient(patient);
        }
        catch (Exception e){
            ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(patient1));
    }

    @GetMapping("/getPatientById/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
        Patient patient;
        try {
            patient = patientService.getPatientById(patientId);
            return ResponseEntity.of(Optional.of(patient));
        }
        catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/getPatientsByName/{patientName}")
    public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable String patientName){
        List<Patient> patientList = new ArrayList<>();
        try{
            patientList = patientService.getPatientsByName(patientName);
            return ResponseEntity.of(Optional.of(patientList));
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
