package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;
    private HospitalService hospitalService;

    public DoctorService(DoctorRepository doctorRepository, HospitalService hospitalService) {
        this.doctorRepository = doctorRepository;
        this.hospitalService = hospitalService;
    }

    public Doctor getDoctorById(int doctorId){
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if(doctor.isEmpty()){
            throw new ResourceNotFoundException("No doctor with id: "+ doctorId+ " found");
        }

        return doctor.get();
    }

    public Doctor addDoctor(Doctor doctor, int hospitalId) throws RuntimeException{

        Hospital hospital = hospitalService.getHospitalById(hospitalId);

        doctor.setHospital(hospital);
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

    public Doctor getDoctorByUserId(String doctorUserId){
        Optional<Doctor> doctor= doctorRepository.findByUserId(doctorUserId);

        if(doctor.isEmpty()){
            throw new ResourceNotFoundException("No doctor with userID: "+ doctorUserId+" found");
        }

        return doctor.get();
    }
}
