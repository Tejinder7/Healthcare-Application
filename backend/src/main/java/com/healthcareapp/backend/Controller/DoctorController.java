package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/addDoctor/{userId}")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @PathVariable String userId){
        Doctor savedDoctor;

        try{
            savedDoctor = doctorService.addDoctor(doctor, userId);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(savedDoctor));
    }

    @PutMapping("/updateDoctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor){
        Doctor updatedDoctor;

        try{
            updatedDoctor = doctorService.updateDoctor(doctor);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedDoctor));
    }
}
