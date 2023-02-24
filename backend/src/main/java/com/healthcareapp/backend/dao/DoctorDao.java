package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface DoctorDao extends CrudRepository<Doctor, Integer> {

    public Hospital findByDoc_id(int id);

}
