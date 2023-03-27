package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.HospitalDao;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Hospital addHospital(int hospId, String name, String address){

        Hospital hosp = new Hospital();

        hosp.setHospId(hospId);
        hosp.setAddress(address);
        hosp.setName(name);

        try {
            hosp = hospitalDao.save(hosp);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
        return hosp;
    }

}