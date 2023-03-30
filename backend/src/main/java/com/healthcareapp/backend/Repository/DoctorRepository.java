package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    public Doctor findDoctorByAuthId(int authId);

}