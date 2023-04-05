package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;

    private HospitalRepository hospitalRepository;

    public DoctorService(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Doctor getDoctorByAuthId(int authId){
        Doctor doctor = doctorRepository.findDoctorByAuthId(authId);
        if(doctor == null){
            throw new RuntimeException();
        }
        return doctor;
    }

    public Hospital getHospitalByDocId(int authId){
        Doctor doctor = getDoctorByAuthId(authId);
        Hospital hospital = doctor.getHospital();

        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }


    public Doctor addDoctor(Doctor doctor, int hospitalId){

        Hospital hospital = hospitalRepository.getHospitalsByHospId(hospitalId);
        if(hospital == null){
            throw new RuntimeException();
        }

        doctor.setHospital(hospital);
        doctor.setUserType("Doctor");

        try{
            doctor = doctorRepository.save(doctor);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        return doctor;
    }

    public List<Doctor> getAllDoctorsByHospital(Hospital hospital){
        List<Doctor> doctorList;
        try{
            doctorList = doctorRepository.findDoctorByHospital(hospital);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return doctorList;
    }

    public Doctor updateDoctor(Doctor doctor){
        Doctor doctor1 = doctorRepository.findDoctorByAuthId(doctor.getAuthId());
        doctor1.setDocSpecialization(doctor.getDocSpecialization());
        doctor1.setName(doctor.getName());
        doctor1.setLicId(doctor.getLicId());
        doctor1.setPhoneNum(doctor.getPhoneNum());
        doctor1.setUserId(doctor.getUserId());
        doctor1.setPassword(doctor.getPassword());
        try {
            doctorRepository.save(doctor1);
            return doctor1;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}
