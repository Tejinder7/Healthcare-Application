package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin
public class DoctorController {
    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestParam("name") String name, @RequestParam("licId") String licId, @RequestParam("phoneNum") String phoneNum, @RequestParam("docSpecialization") String docSpecialization, @RequestParam("hospId") int hospId, @RequestParam("userId") String userId, @RequestParam("password") String password){
        Doctor doc;

        try{
            doc = doctorService.addDoctor(name, licId, phoneNum, docSpecialization, hospId, userId, password);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(doctor1));
    }
}
