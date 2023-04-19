package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Security.auth.AuthenticationService;
import com.healthcareapp.backend.Security.auth.JwtResponse;
import com.healthcareapp.backend.Service.AuthorizationService;
import com.healthcareapp.backend.Service.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
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
