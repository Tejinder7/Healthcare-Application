package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;

    private HospitalRepository hospitalRepository;

    public DoctorService(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Doctor getDoctorByAuthId(int authId){
        Optional<Doctor> doctor = doctorRepository.findById(authId);

        if(doctor.isEmpty()){
            throw new ResourceNotFoundException("No doctor with id: "+ authId+ " found");
        }

        return doctor.get();
    }

    public Hospital getHospitalByDocId(int authId){
        Doctor doctor = getDoctorByAuthId(authId);
        Hospital hospital = doctor.getHospital();

        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }


    public Doctor addDoctor(Doctor doctor, int hospitalId) throws RuntimeException{

        Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
        if(hospital == null){
            throw new ResourceNotFoundException("No Hospital found for Hospital Id: "+ hospitalId);
        }

        doctor.setHospital(hospital.get());
        doctor.setUserType("Doctor");

        Doctor savedDoctor= doctorRepository.save(doctor);
        return savedDoctor;
    }

    public List<Doctor> getAllDoctorsByHospital(Hospital hospital){

        List<Doctor> doctorList= doctorRepository.findByHospital(hospital);

        return doctorList;
    }

    public Doctor updateDoctor(Doctor doctor) throws RuntimeException{
        Optional<Doctor> updatedDoctor = doctorRepository.findById(doctor.getAuthId());

        if(updatedDoctor.isEmpty()){
            throw new ResourceNotFoundException("No Doctor with id: "+ doctor.getAuthId()+ " found");
        }

        updatedDoctor.get().setDocSpecialization(doctor.getDocSpecialization());
        updatedDoctor.get().setName(doctor.getName());
        updatedDoctor.get().setLicId(doctor.getLicId());
        updatedDoctor.get().setContact(doctor.getContact());
        updatedDoctor.get().setUserId(doctor.getUserId());
        updatedDoctor.get().setPassword(doctor.getPassword());

        doctorRepository.save(updatedDoctor.get());
        return updatedDoctor.get();
    }
}
