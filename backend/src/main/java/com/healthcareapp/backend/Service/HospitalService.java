package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HospitalService {
    private HospitalRepository hospitalRepository;

    private SupervisorRepository supervisorRepository;


    public HospitalService(HospitalRepository hospitalRepository, SupervisorRepository supervisorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.supervisorRepository = supervisorRepository;
    }

    public Hospital getHospitalById(int id){
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        if(hospital.isEmpty()){
            throw new ResourceNotFoundException("No Hospital found for Hospital id: "+ id);
        }
        return hospital.get();
    }

    public Hospital addHospital(Hospital hospital) throws RuntimeException{
        Hospital savedHospital;
        Optional<Supervisor> supervisor = supervisorRepository.findByPincode(hospital.getPincode());

        if(supervisor.isPresent())
            hospital.setSupId(supervisor.get());

        savedHospital = hospitalRepository.save(hospital);

        return savedHospital;
    }

    public List<Hospital> getAllHospitals() throws RuntimeException{
        List<Hospital> hospitalList = new ArrayList<>();
        hospitalList = hospitalRepository.findAll();
        return hospitalList;
    }

    public Optional<List<Hospital>> getHospitalsWithPincode(Supervisor supervisor) throws RuntimeException{
        Optional<List<Hospital>> hospitalList = hospitalRepository.findByPincode(supervisor.getPincode());
        for (int i=0; i<hospitalList.get().size(); i++){
            Hospital hospital = hospitalList.get().get(i);
            hospital.setSupId(supervisor);
            hospitalRepository.save(hospital);
        }
        return hospitalList;
    }
}