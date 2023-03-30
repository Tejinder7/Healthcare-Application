package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Optional;
@RestController
@CrossOrigin
public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/addDoctor/{hospitalId}")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor, @PathVariable int hospitalId){
        Doctor doctor1;

        try{
            doctor1 = doctorService.addDoctor(doctor, hospitalId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(doctor1));
    }
}
