package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

@Component
public class HospitalService {
    private HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
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

        hospital.setAddress(address);
        hospital.setName(name);

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