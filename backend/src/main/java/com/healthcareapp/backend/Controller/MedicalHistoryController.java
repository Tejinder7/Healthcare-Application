package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Service.MedicalHistoryService;
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
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("getMedicalHistory/{pid}")
    public ResponseEntity<List<MedicalHistory>> getMedicalHistory(@PathVariable int pid){
        List<MedicalHistory> mhList;
        try {
            mhList = medicalHistoryService.getMedicalHistoryByPatientId(pid);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(mhList));
    }

}