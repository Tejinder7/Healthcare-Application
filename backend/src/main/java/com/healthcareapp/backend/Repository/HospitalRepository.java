package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    public Hospital getHospitalsByHospId(int id);
}

