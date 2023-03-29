package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Service.FrontDeskService;
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
    private FrontDeskService frontDeskServices;

    public FrontDeskController(FrontDeskService frontDeskServices) {
        this.frontDeskServices = frontDeskServices;
    }

    @PostMapping("/addFrontDesk")
    public ResponseEntity<FrontDesk> addFrontDesk(@RequestParam("name") String name, @RequestParam("hospId") int hospId, @RequestParam("userId") String userId, @RequestParam("password") String password) {
        FrontDesk frontDesk;

        try {
            frontDesk = frontDeskServices.addFrontDesk(name, hospId, userId, password);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(frontDesk));
    }
}
