package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
    public Optional<Authorization> findByUsername(String username);
}
