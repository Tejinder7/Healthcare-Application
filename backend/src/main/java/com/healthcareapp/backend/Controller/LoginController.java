package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Service.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
