package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorServices doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestParam("name") String name, @RequestParam("licId") String licId, @RequestParam("phoneNum") String phoneNum, @RequestParam("docSpecialization") String docSpecialization, @RequestParam("hospId") int hospId){
        Doctor doc;

        try{
            doc = doctorService.addDoctor(name, licId, phoneNum, docSpecialization, hospId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(doc));
    }
}
