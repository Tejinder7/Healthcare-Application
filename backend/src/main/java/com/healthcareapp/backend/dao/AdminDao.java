package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Integer> {



}
