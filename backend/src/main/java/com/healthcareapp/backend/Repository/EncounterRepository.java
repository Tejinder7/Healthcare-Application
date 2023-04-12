package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.List;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {
    public Encounter getEncounterByEncounterId(int id);

    public List<Encounter> findByDoctorAndFlagIsFalse(Doctor doctor);

    public List<Encounter> findByDoctor(Doctor doctor);

    public List<Encounter> findByPatient(Patient patient);
}
