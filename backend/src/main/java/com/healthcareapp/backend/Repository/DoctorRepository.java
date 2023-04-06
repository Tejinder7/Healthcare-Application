package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findDoctorByAuthId(int authId);

    public List<Doctor> findByHospital(Hospital hospital);

}