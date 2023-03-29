package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {
    private AuthorizationRepository authorizationRepository;

    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }
    public boolean loginAuthorization(Authorization authorization){
        Authorization authorization1= authorizationRepository.findByUserIdAndPasswordAndUserType(authorization.getUserId(), authorization.getPassword(), authorization.getUserType());

        if(authorization1== null){
            return false;
        }
        return true;
    }
}
