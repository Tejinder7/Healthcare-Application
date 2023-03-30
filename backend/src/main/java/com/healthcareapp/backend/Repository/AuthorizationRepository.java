package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
    public Authorization findByUserIdAndPasswordAndUserType(String userId, String password, String userType);
}
