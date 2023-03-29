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
    private HospitalRepository hospitalRepository;

 
    private HospitalRepository hospitalDao;
    private SupervisorRepository supervisorRepository;


    public HospitalService(HospitalRepository hospitalRepository, SupervisorRepository supervisorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.supervisorRepository = supervisorRepository;
    }

    public Hospital getHospitalById(int id){
        Hospital hospital = hospitalRepository.getHospitalsByHospId(id);
        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }

    public Hospital addHospital(String name, String address){

        Hospital hospital = new Hospital();


        Supervisor supervisor = supervisorRepository.findByAddress(address);

        hosp.setAddress(address);
        hosp.setName(name);
        hosp.setSupId(supervisor);


        try {
            hospital = hospitalRepository.save(hospital);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
        return hospital;
    }

}