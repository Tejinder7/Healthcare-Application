package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Security.auth.AuthenticationResponse;
import com.healthcareapp.backend.Security.auth.AuthenticationService;
import com.healthcareapp.backend.Service.AuthorizationService;
import com.healthcareapp.backend.Service.SuperAdminService;
import com.healthcareapp.backend.Service.SupervisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody Authorization request){
        System.out.println(request.getRole());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @CrossOrigin(origins = "localhost")
    @PostMapping("/addSuperAdmin")
    public SuperAdmin addSuperAdmin(@RequestBody SuperAdmin superAdmin){
        superAdminService.addSuperAdmin(superAdmin);
        return superAdmin;
    }

//    @PostMapping()
//    public Authorization loginUser(@RequestBody Authorization authorization){
//        try {
//            return authorizationService.loginAuthorization(authorization);
//        }catch (Exception exception){
//            throw exception;
//        }
//    }

//    @PostMapping("/forgotPassword/{email}")
//    public List<Object> forgotPassword(@PathVariable String email){
//        List<Object> list = new ArrayList<>();
//        Boolean boolValue = true;
//        list.add(boolValue);
//        String msg = "Please check email to change the password";
//        list.add(msg);
//        return list;
//    }
}
