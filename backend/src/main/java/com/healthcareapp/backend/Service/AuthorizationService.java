package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
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

    public void checkIfUserIdExists(String username){
        Optional<Authorization> authorization = authorizationRepository.findByUsername(username);

        if(authorization.isPresent()){
            throw new ForbiddenException("User already exists. Please try again with a different User id");
        }
    }
}
