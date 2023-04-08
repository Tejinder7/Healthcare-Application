package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {
    public Encounter getEncounterByEncounterId(int id);

    public List<Encounter> findByDoctor(Doctor doctor);
}
