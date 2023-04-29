package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Encryption.AESUtil;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.SuperAdmin;
import com.healthcareapp.backend.Security.auth.AuthenticationService;
import com.healthcareapp.backend.Security.auth.JwtResponse;
import com.healthcareapp.backend.Service.AuthorizationService;
import com.healthcareapp.backend.Service.FollowUpService;
import com.healthcareapp.backend.Service.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    AuthorizationService authorizationService;
    AuthenticationService authenticationService;

    SuperAdminService superAdminService;

    AESUtil aesUtil;

    FollowUpService followUpService;

    public LoginController(AuthorizationService authorizationService, AuthenticationService authenticationService, SuperAdminService superAdminService, FollowUpService followUpService, AESUtil aesUtil) {
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        this.superAdminService = superAdminService;
        this.aesUtil = aesUtil;
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

    @GetMapping("/test/{str}")
    public String testing(@PathVariable String str) throws Exception {
//        String en = aesUtil.encrypt("password", str);
        String de = aesUtil.decrypt("password", str);
        return de;
    }

    @PostMapping("/addSuperAdmin")
    public SuperAdmin addSuperAdmin(@RequestBody SuperAdmin superAdmin) throws Exception {
        SuperAdmin superAdmin1 = superAdminService.addSuperAdmin(superAdmin);
//        SealedObject sealedObject = sealedObjectConversions.encryptObject(superAdmin);
//        SuperAdmin superAdminDecrypted = (SuperAdmin)sealedObjectConversions.decryptObject(sealedObject);
        return superAdmin1;
    }
}
