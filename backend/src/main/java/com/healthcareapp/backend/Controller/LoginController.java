package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Encryption.CryptoEncryption;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Security.auth.AuthenticationResponse;
import com.healthcareapp.backend.Security.auth.AuthenticationService;
import com.healthcareapp.backend.Service.AuthorizationService;
import com.healthcareapp.backend.Service.SuperAdminService;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import com.healthcareapp.backend.Security.auth.JwtResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    AuthorizationService authorizationService;
    AuthenticationService authenticationService;

    SuperAdminService superAdminService;

    public LoginController(AuthorizationService authorizationService, AuthenticationService authenticationService, SuperAdminService superAdminService) {
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        this.superAdminService = superAdminService;
    }

    @PostMapping("/")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody Authorization request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @CrossOrigin(origins = "localhost")
    @PostMapping("/addSuperAdmin")
    public SuperAdmin addSuperAdmin(@RequestBody SuperAdmin superAdmin){
        superAdminService.addSuperAdmin(superAdmin);
        return superAdmin;
    }

}
