package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Optional<Admin> findByUsername(String username);
}
