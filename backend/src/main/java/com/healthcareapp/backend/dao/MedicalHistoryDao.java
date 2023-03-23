package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Integer> {

    public MedicalHistory findMedicalHistoriesByPatientId(Patient patient);

}
