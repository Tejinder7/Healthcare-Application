package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.services.MedicalHistoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryServices medicalHistoryServices;

    @GetMapping("getMedicalHistory/{pid}")
    public ResponseEntity<List<MedicalHistory>> getMedicalHistory(@PathVariable int pid){
        List<MedicalHistory> mhList;
        try {
            mhList = medicalHistoryServices.getMedicalHistoryByPatientId(pid);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(mhList));
    }

}
