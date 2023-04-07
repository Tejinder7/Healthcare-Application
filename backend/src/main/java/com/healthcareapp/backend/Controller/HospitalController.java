package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Hospital savedHospital;
        try{
            savedHospital = hospitalService.addHospital(hospital);
        }catch (RuntimeException exception)
        {
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedHospital));
    }

    @GetMapping("/hospitalsWithNoAdmins")
    public List<Hospital> getHospitalsWithNoAdmins(){
        List<Hospital> hospitalList;

        hospitalList = hospitalService.getHospitalsWhereAdminNotAssigned();

        return hospitalList;
    }
}
