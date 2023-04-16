package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
}
