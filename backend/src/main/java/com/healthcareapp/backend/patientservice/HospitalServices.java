package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.HospitalDao;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalServices {

    @Autowired
    private HospitalDao hospitalDao;

    public Hospital getHospitalById(int id){
        return hospitalDao.findByHospId(id);
    }
}
