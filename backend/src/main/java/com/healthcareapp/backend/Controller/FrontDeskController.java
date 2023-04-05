package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Service.FrontDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FrontDeskController {
    private FrontDeskService frontDeskServices;

    public FrontDeskController(FrontDeskService frontDeskServices) {
        this.frontDeskServices = frontDeskServices;
    }

    @PostMapping("/addFrontDesk/{hospitalId}")
    public ResponseEntity<FrontDesk> addFrontDesk(@RequestBody FrontDesk frontDesk, @PathVariable int hospitalId) {

        try {
            frontDesk = frontDeskServices.addFrontDesk(frontDesk, hospitalId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(frontDesk));
    }
}
