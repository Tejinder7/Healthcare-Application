package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Integer> {

    public List<FollowUp> findByDateAndPatient(String date, Patient patient);

    public FollowUp findById(int id);

    public List<FollowUp> findByHospitalAndFlagIsFalse(Hospital hospital);

    public List<FollowUp> findByPatient(Patient patient);
}
