package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterDao extends JpaRepository<Encounter, Integer> {
    public Encounter getEncounterByEncounterId(int id);
}
