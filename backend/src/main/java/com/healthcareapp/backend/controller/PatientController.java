package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.patientservice.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientServices patientServices;

    @PostMapping("/addPatients")
    public Patient addPatient(@RequestBody Patient patient){
        Patient p = this.patientServices.addPatient(patient);
        return p;
    }

}
