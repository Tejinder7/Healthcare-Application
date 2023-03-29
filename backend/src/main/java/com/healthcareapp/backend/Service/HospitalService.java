package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalDao;

    public Hospital getHospitalById(int id){
        Hospital hospital = hospitalDao.getHospitalsByHospId(id);
        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }

    public Hospital addHospital(String name, String address){

        Hospital hosp = new Hospital();

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