package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Encryption.ObjectEncryption;
import com.healthcareapp.backend.Encryption.SealedObjectConversions;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Security.auth.AuthenticationService;
import com.healthcareapp.backend.Security.auth.JwtResponse;
import com.healthcareapp.backend.Service.AuthorizationService;
import com.healthcareapp.backend.Service.FollowUpService;
import com.healthcareapp.backend.Service.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SealedObject;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    AuthorizationService authorizationService;
    AuthenticationService authenticationService;

    SuperAdminService superAdminService;

    ObjectEncryption objectEncryption;

    SealedObjectConversions sealedObjectConversions;

    FollowUpService followUpService;

    public LoginController(AuthorizationService authorizationService, AuthenticationService authenticationService, SuperAdminService superAdminService, FollowUpService followUpService, ObjectEncryption objectEncryption, SealedObjectConversions sealedObjectConversions) {
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        this.superAdminService = superAdminService;
        this.objectEncryption = objectEncryption;
        this.sealedObjectConversions = sealedObjectConversions;
        this.followUpService = followUpService;
    }

    @PostMapping("/")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody Authorization request){
        JwtResponse response;
        try{
            response= authenticationService.authenticate(request);
        }catch(Exception exception){
            throw exception;
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tp")
    public List<FollowUp> tp(){
        return followUpService.getAllTodayFollowUps();
    }

    @PostMapping("/addSuperAdmin")
    public SuperAdmin addSuperAdmin(@RequestBody SuperAdmin superAdmin) throws Exception {
        SuperAdmin superAdmin1 = superAdminService.addSuperAdmin(superAdmin);
//        SealedObject sealedObject = sealedObjectConversions.encryptObject(superAdmin);
//        SuperAdmin superAdminDecrypted = (SuperAdmin)sealedObjectConversions.decryptObject(sealedObject);
        return superAdmin1;
    }
}
