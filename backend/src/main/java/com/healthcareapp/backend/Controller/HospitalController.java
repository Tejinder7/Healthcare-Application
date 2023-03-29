package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/addHospital")
    public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital){
        Hospital hosp;
        try{
            hosp = hospitalService.addHospital(hospital.getName(), hospital.getAddress());
        }
        catch (Exception e)
        {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(hosp));
    }
}
