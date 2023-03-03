package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.patientservice.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientServices patientServices;

    @PostMapping("/addPatients")
    public Patient addPatient(@RequestBody Patient patient){
        Patient p = this.patientServices.addPatient(patient);
        return p;
    }

}
