package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findPatientByPatientId(int id);
}
