package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findPatientByPatientId(int id);

    public List<Patient> findByFieldWorker(FieldWorker fieldWorker);
}
