package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.AdminRepository;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.FrontDeskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminService {
    AdminRepository adminRepository;
    DoctorRepository doctorRepository;
    FrontDeskRepository frontDeskRepository;
    HospitalService hospitalService;
    AuthorizationService authorizationService;

    public AdminService(AdminRepository adminRepository, HospitalService hospitalService, AuthorizationService authorizationService, DoctorRepository doctorRepository, FrontDeskRepository frontDeskRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.frontDeskRepository = frontDeskRepository;
        this.hospitalService = hospitalService;
        this.authorizationService = authorizationService;
    }

    public Admin addAdmin(Admin admin, int hospId) throws RuntimeException{
        authorizationService.checkIfUserIdExists(admin.getUserId());

        Hospital hospital= hospitalService.getHospitalById(hospId);

        admin.setHospital(hospital);
        admin.setUserType("Admin");

        Admin savedAdmin= adminRepository.save(admin);

        return savedAdmin;
    }

    public Admin updateAdmin(Admin admin) throws RuntimeException{
        Admin updatedAdmin= adminRepository.save(admin);

        return updatedAdmin;
    }

    public List<Object> getAllHospitalUsers(String userId) throws RuntimeException{
        List<Doctor> doctorList;
        List<FrontDesk> frontDeskList;
        Hospital hospital;

        Optional<Admin> admin = adminRepository.findByUserId(userId);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Admin with userId: "+ userId+ " not found");
        }

        hospital = hospitalService.getHospitalById(admin.get().getHospital().getHospId());

        doctorList = doctorRepository.findByHospital(hospital);
        frontDeskList = frontDeskRepository.findByHospital(hospital);

        List<Object> userList = new ArrayList<>();

        userList.addAll(doctorList);
        userList.addAll(frontDeskList);

        return userList;
    }

    public List<Admin> getListOfAdmins(){
        List<Admin> adminList= adminRepository.findAll();

        return adminList;
    }


    public List<Hospital> getHospitalsWhereAdminNotAssigned(){
        List<Hospital> hospitalList = hospitalService.getAllHospitals();
        List<Admin> adminList = this.getListOfAdmins();

        List<Hospital> hospitalAssignedList = new ArrayList<>();

        adminList.forEach(admin -> hospitalAssignedList.add(admin.getHospital()));

        List<Hospital> hospitalNotAssignedList = new ArrayList<>(hospitalList);
        hospitalNotAssignedList.removeAll(hospitalAssignedList);

        return hospitalNotAssignedList;
    }

    public Admin getAdminByUserId(String userId){
        Optional<Admin> admin = adminRepository.findByUserId(userId);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Admin with userId: "+ userId+ " not found");
        }

        return admin.get();
    }
}
