package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

    public List<MedicalHistory> findByPatient(Patient patient);
    public MedicalHistory findMedicalHistoryByEncounter(Encounter encounter);

}
