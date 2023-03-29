package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class PatientController {
    private PatientService patientService;

    @PostMapping("/addPatients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        try {
            Patient patient1 = this.patientService.addPatient(patient);
        }
        catch (Exception e){
            ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(patient));
    }
}
