package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientServices patientServices;

    @PostMapping("/addPatients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        try {
            Patient p = this.patientServices.addPatient(patient);
        }
        catch (Exception e){
            ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(patient));
    }
}
