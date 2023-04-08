package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findAdminByAuthId(int authId);

    public Admin findAdminByUserId(String userId);
}
