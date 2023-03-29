package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HospitalController {
    private HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping("/addHospital")
    public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital){
        Hospital hospital1;
        try{
            hospital1 = hospitalService.addHospital(hospital);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(hospital1));
    }
}
