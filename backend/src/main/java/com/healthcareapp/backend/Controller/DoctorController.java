package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor/{hospId}")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @PathVariable("hospId") int hospId){
        Doctor doc;

        try{
            doc = doctorService.addDoctor(doctor.getName(), doctor.getLicId(), doctor.getPhoneNum(), doctor.getDocSpecialization(), hospId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(doc));
    }
}
