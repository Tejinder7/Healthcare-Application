package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findAdminByAuthId(int authId);

    public Optional<Admin> findByUsername(String username);
}
