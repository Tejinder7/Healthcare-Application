package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {
    private AuthorizationRepository authorizationRepository;

    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }
    public Authorization loginAuthorization(Authorization authorization){
        Authorization authorization1= authorizationRepository.findByUserIdAndPasswordAndUserType(authorization.getUserId(), authorization.getPassword(), authorization.getUserType());

        if(authorization1== null){
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
        return authorization1;
    }

    public Authorization getAuthorizationById(String userId){
        Authorization authorization = authorizationRepository.findAuthorizationByUserId(userId);
        if(authorization== null){
            throw new ResourceNotFoundException("USER NOT FOUND");
        }
        return authorization;
    }
}
