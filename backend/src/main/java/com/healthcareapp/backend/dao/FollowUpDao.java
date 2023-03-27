package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.FollowUp;
import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FollowUpDao extends JpaRepository<FollowUp, Integer> {

    public List<FollowUp> findByDateAndPatientId(String date, Patient patient);

    public FollowUp findById(int id);

    public FollowUp findByHospId(Hospital hospital);
}
