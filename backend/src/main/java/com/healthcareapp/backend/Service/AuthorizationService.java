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
    public Authorization loginAuthorization(Authorization authorization){
        Optional<Authorization> user= authorizationRepository.findByUserIdAndPasswordAndUserType(authorization.getUserId(), authorization.getPassword(), authorization.getUserType());

        if(user.isEmpty()){
            throw new ResourceNotFoundException("Invalid Credentials. Please try again with valid credentials");
        }
        return user.get();
    }

    public Optional<Authorization> getAuthorizationById(String userId){
        Optional<Authorization> authorization = authorizationRepository.findByUserId(userId);

        return authorization;
    }
}
