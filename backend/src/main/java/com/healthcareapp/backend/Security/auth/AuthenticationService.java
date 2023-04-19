package com.healthcareapp.backend.Security.auth;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import com.healthcareapp.backend.Security.Configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthorizationRepository authorizationRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtResponse authenticate(Authorization request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Authorization user = authorizationRepository.findByUsername(request.getUsername())
                .orElseThrow();

        if(user.getRole() != request.getRole()){
            throw new ResourceNotFoundException("Invalid Credentials. Please try again with valid credentials");
        }

        var jwtToken = jwtService.createToken(user);

//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

//        authenticationResponse.setUsername(user.getUsername());
//        authenticationResponse.setToken(jwtToken);

        return new JwtResponse(jwtToken, user.getUsername());
    }

}
