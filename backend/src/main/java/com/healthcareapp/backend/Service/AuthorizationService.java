package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationService {
    private AuthorizationRepository authorizationRepository;

    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }
//    public Authorization loginAuthorization(Authorization authorization){
//        Optional<Authorization> user= authorizationRepository.findByUsernameAndPasswordAndRole(authorization.getUsername(), authorization.getPassword(), authorization.getRole());
//
//        if(user.isEmpty()){
//            throw new ResourceNotFoundException("Invalid Credentials. Please try again with valid credentials");
//        }
//        return user.get();
//    }

    public void checkIfUserIdExists(String username){
        Optional<Authorization> authorization = authorizationRepository.findByUsername(username);

        if(authorization.isPresent()){
            throw new ForbiddenException("User already exists. Please try again with a different User id");
        }
    }
}
