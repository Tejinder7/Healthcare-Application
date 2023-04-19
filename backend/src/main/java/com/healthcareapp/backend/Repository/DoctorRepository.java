package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public List<Doctor> findByHospital(Hospital hospital);

    public Optional<Doctor> findByUsername(String userId);

}