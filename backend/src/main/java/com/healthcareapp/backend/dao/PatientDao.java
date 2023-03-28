package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {
    public Patient findPatientByPatientId(int id);
}
