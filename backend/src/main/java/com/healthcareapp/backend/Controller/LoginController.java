package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    AuthorizationService authorizationService;

    public LoginController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public Authorization loginUser(@RequestBody Authorization authorization){
        try {
            return authorizationService.loginAuthorization(authorization);
        }catch (Exception exception){
            throw exception;
        }
    }

    @PostMapping("/forgotPassword/{email}")
    public List<Object> forgotPassword(@PathVariable String email){
        List<Object> list = new ArrayList<>();
        Boolean boolValue = true;
        list.add(boolValue);
        String msg = "Please check email to change the password";
        list.add(msg);
        return list;
    }
}
