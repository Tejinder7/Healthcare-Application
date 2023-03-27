package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.FrontDesk;
import com.healthcareapp.backend.services.FrontDeskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class FrontDeskController {

    @Autowired
    private FrontDeskServices frontDeskServices;

    @PostMapping("/addFrontDesk")
    public ResponseEntity<FrontDesk> addFrontDesk(@RequestParam("name") String name, @RequestParam("hospId") int hospId) {
        FrontDesk frontDesk;

        try {
            frontDesk = frontDeskServices.addFrontDesk(name, hospId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(frontDesk));
    }
}
