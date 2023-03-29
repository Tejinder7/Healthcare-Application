package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalDao;

    @Autowired
    private SupervisorRepository supervisorRepository;

    public Hospital getHospitalById(int id){
        Hospital hospital = hospitalDao.getHospitalsByHospId(id);
        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }

    public Hospital addHospital(String name, String address){

        Hospital hosp = new Hospital();

        Supervisor supervisor = supervisorRepository.findByAddress(address);

        hosp.setAddress(address);
        hosp.setName(name);
        hosp.setSupId(supervisor);


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