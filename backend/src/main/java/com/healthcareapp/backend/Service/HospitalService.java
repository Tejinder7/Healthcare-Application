package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HospitalService {
    private HospitalRepository hospitalRepository;

    private SupervisorService supervisorService;

    private AdminService adminService;

    public HospitalService(HospitalRepository hospitalRepository, SupervisorService supervisorService, AdminService adminService) {
        this.hospitalRepository = hospitalRepository;
        this.supervisorService = supervisorService;
        this.adminService = adminService;
    }

    public Hospital getHospitalById(int id){
        Optional<Hospital> hospital = hospitalRepository.findById(id);

        if(hospital == null){
            throw new ResourceNotFoundException("No Hospital found for Hospital id: "+ id);
        }
        return hospital.get();
    }

    public Hospital addHospital(Hospital hospital) throws RuntimeException{
        Hospital savedHospital;
        Supervisor supervisor = supervisorService.getSupervisorByAddress(hospital.getAddress());

        hospital.setSupId(supervisor);

        savedHospital = hospitalRepository.save(hospital);

        return savedHospital;
    }

    public List<Hospital> getHospitalsWhereAdminNotAssigned(){
        List<Hospital> hospitalList = hospitalRepository.findAll();
        List<Admin> adminList = adminService.getListOfAdmins();

        List<Hospital> hospitalAssignedList = new ArrayList<>();

        adminList.forEach(admin -> {hospitalAssignedList.add(admin.getHospital());});

        List<Hospital> hospitalNotAssignedList = new ArrayList<>(hospitalList);
        hospitalNotAssignedList.removeAll(hospitalAssignedList);

        return hospitalNotAssignedList;
    }
}