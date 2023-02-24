package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDao extends JpaRepository<Hospital, Integer> {
    public Hospital findByHospId(int id);
}
