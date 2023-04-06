package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HospitalService {
    private HospitalRepository hospitalRepository;

    private SupervisorRepository supervisorRepository;

    @Autowired
    private AdminRepository adminRepository;


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

    public Hospital addHospital(Hospital hospital){

        Supervisor supervisor = supervisorRepository.findByAddress(hospital.getAddress());

        if(supervisor == null){
            throw new RuntimeException();
        }

        hospital.setAddress(hospital.getAddress());
        hospital.setName(hospital.getName());
        hospital.setSupId(supervisor);


        try {
            hospital = hospitalRepository.save(hospital);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
        return hospital;
    }

    public List<Hospital> getHospitalsWhereAdminNotAssigned(){
        List<Hospital> hospitalList = hospitalRepository.findAll();
        List<Admin> adminList = adminRepository.findAll();
        List<Hospital> hospitalAssignedList = new ArrayList<>();

        adminList.forEach(admin -> {hospitalAssignedList.add(admin.getHospital());});

        List<Hospital> hospitalNotAssignedList = new ArrayList<>(hospitalList);
        hospitalNotAssignedList.removeAll(hospitalAssignedList);

        return hospitalNotAssignedList;
    }
}