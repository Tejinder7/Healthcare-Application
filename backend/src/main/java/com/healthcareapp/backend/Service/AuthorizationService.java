package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorizationService {
    private AuthorizationRepository authorizationRepository;
    private ValidationHelper validationHelper;

    public AuthorizationService(AuthorizationRepository authorizationRepository, ValidationHelper validationHelper) {
        this.authorizationRepository = authorizationRepository;
        this.validationHelper = validationHelper;
    }

    public void checkIfUserIdExists(String username){
        validationHelper.usernamePasswordValidation(username);
        Optional<Authorization> authorization = authorizationRepository.findByUsername(username);

        if(authorization.isPresent()){
            throw new ForbiddenException("User already exists. Please try again with a different User id");
        }
    }
}
