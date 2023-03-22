package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.HospitalDao;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class HospitalServices {

    @Autowired
    private HospitalDao hospitalDao;

    public Hospital getHospitalById(int id){
        Hospital hospital = hospitalDao.getHospitalsByHospId(id);
        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }

}
