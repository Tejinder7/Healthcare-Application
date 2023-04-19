package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Authorization;
import com.healthcareapp.backend.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
    public Optional<Authorization> findByUsernameAndPasswordAndRole(String userId, String password, Role role);

    public Optional<Authorization> findByUsername(String username);
}
