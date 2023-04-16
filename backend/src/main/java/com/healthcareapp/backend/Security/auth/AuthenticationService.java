package com.healthcareapp.backend.Security.auth;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.Role;
import com.healthcareapp.backend.Repository.AuthorizationRepository;
import com.healthcareapp.backend.Security.Configuration.JwtService;
import com.healthcareapp.backend.Security.token.Token;
import com.healthcareapp.backend.Security.token.TokenRepository;
import com.healthcareapp.backend.Security.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthorizationRepository authorizationRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(RegisterRequest request){
//        Authorization authorization = new Authorization();
//        authorization.setUsername(request.getUserId());
//        authorization.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        String roleString = request.getRole().toLowerCase();
//        Role role = Role.ADMIN;
//        if(roleString.equals("super admin")){
//            role = Role.SUPER_ADMIN;
//        }
//        authorization.setRole(role);
//
//
//        Authorization savedUser = authorizationRepository.save(authorization);
//        var jwtToken = jwtService.generateToken(authorization);
//
//        saveUserToken(savedUser, jwtToken);
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(Authorization request) {
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

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);

        saveUserToken(user, jwtToken);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setUsername(user.getUsername());
        authenticationResponse.setToken(jwtToken);

        return authenticationResponse;
    }

    private void revokeAllUserTokens(Authorization user){
        Optional<Authorization> authorization = authorizationRepository.findByUsername(user.getUsername());
        Optional<List<Token>> validUserTokens = tokenRepository.findAllValidTokensByAuthorizationAndRevokedIsFalseAndExpiredIsFalse(authorization.get());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.get().forEach(t-> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens.get());
    }

    private void saveUserToken(Authorization user, String jwtToken) {
        var token = Token.builder()
                .authorization(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
