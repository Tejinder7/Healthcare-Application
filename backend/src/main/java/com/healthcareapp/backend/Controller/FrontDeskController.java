package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Service.FrontDeskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FrontDeskController {
    private FrontDeskService frontDeskService;

    public FrontDeskController(FrontDeskService frontDeskServices) {
        this.frontDeskService = frontDeskServices;
    }

    @PostMapping("/addFrontDesk/{hospitalId}")
    public ResponseEntity<FrontDesk> addFrontDesk(@RequestBody FrontDesk frontDesk, @PathVariable int hospitalId) {
        FrontDesk savedFrontDesk;
        try {
            savedFrontDesk = frontDeskService.addFrontDesk(frontDesk, hospitalId);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(savedFrontDesk));
    }

    @PutMapping("/updateFrontDesk")
    public ResponseEntity<FrontDesk> updateFrontDesk(@RequestBody FrontDesk frontDesk){
        FrontDesk updatedFrontDesk;
        try{
            updatedFrontDesk = frontDeskService.updateFrontDesk(frontDesk);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedFrontDesk));
    }
}
