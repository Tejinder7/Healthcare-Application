package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HospitalService {
    private HospitalRepository hospitalRepository;

    private SupervisorRepository supervisorRepository;

    private ValidationHelper validationHelper;

    public HospitalService(HospitalRepository hospitalRepository, SupervisorRepository supervisorRepository, ValidationHelper validationHelper) {
        this.hospitalRepository = hospitalRepository;
        this.supervisorRepository = supervisorRepository;
        this.validationHelper = validationHelper;
    }

    public Hospital getHospitalById(int id){
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        if(hospital.isEmpty()){
            throw new ResourceNotFoundException("Hospital with id: "+ id+ " not found");
        }
        return hospital.get();
    }

    public Hospital addHospital(Hospital hospital) throws RuntimeException{

        validationHelper.nameValidation(hospital.getName());
        validationHelper.strValidation(hospital.getAddress());
        validationHelper.pincodeValidation(hospital.getPincode());

        Hospital savedHospital;
        Optional<Supervisor> supervisor = supervisorRepository.findByPincode(hospital.getPincode());

        if(supervisor.isPresent())
            hospital.setSupId(supervisor.get());

        savedHospital = hospitalRepository.save(hospital);

        return savedHospital;
    }

    public List<Hospital> getAllHospitals() throws RuntimeException{
        List<Hospital> hospitalList = hospitalRepository.findAll();

        return hospitalList;
    }

    public void setSupervisorByPincode(Supervisor supervisor) throws RuntimeException{
        List<Hospital> hospitalList = hospitalRepository.findByPincode(supervisor.getPincode());

        hospitalList.forEach(hospital -> hospital.setSupervisor(supervisor));
        hospitalRepository.saveAll(hospitalList);
    }
}