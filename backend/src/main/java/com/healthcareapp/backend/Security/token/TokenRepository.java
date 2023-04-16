package com.healthcareapp.backend.Security.token;

import com.healthcareapp.backend.Model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    Optional<List<Token>> findAllValidTokensByAuthorizationAndRevokedIsFalseAndExpiredIsFalse(Authorization authorization);


    Optional<Token> findByToken(String token);
}