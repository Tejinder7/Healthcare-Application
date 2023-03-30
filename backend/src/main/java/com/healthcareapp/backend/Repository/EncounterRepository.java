package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {
    public Encounter getEncounterByEncounterId(int id);
}
