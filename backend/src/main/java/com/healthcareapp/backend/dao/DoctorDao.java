package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {

    public Doctor findDoctorByDocId(int id);

}