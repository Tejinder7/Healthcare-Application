package com.healthcareapp.backend.Security.auth;

import com.healthcareapp.backend.Exception.ForbiddenException;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import com.healthcareapp.backend.Security.Configuration.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthorizationRepository authorizationRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthorizationRepository authorizationRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.authorizationRepository = authorizationRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse authenticate(Authorization request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch(Exception exception){
            throw new ForbiddenException("Invalid Credentials. Please try again with valid credentials");
        }

        Authorization user = authorizationRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if(user.getRole() != request.getRole()){
            throw new ResourceNotFoundException("Invalid Credentials. Please try again with valid credentials");
        }

        var jwtToken = jwtService.createToken(user);

        return new JwtResponse(jwtToken, user.getUsername());
    }

}
