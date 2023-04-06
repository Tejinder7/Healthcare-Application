package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
//    public Hospital findByHospId(int id);

}

