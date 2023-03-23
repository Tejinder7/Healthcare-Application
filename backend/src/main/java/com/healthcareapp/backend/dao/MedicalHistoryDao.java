package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Encounter;
import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Integer> {

    public List<MedicalHistory> findMedicalHistoriesByPatientId(Patient patient);
    public MedicalHistory findMedicalHistoryByEncounterId(Encounter encounter);

}
