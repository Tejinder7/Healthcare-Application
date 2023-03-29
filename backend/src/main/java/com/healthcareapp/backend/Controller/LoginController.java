package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    AuthorizationService authorizationService;

    public LoginController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestBody Authorization authorization){
        return authorizationService.loginAuthorization(authorization);
    }
}
