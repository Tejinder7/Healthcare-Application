package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;

    private HospitalRepository hospitalRepository;

    public DoctorService(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Doctor getDoctorByDocId(int docId){
        Doctor doctor = doctorRepository.findDoctorByDocId(docId);
        if(doctor == null){
            throw new RuntimeException();
        }
        return doctor;
    }

    public Hospital getHospitalByDocId(int docId){
        Doctor doctor = getDoctorByDocId(docId);
        Hospital hospital = doctor.getHospId();

        if(hospital == null){
            throw new RuntimeException();
        }
        return hospital;
    }

    public Doctor addDoctor(Doctor doctor, int hospId){
        Hospital hospital = hospitalRepository.getHospitalsByHospId(hospId);

        if(hospital == null){
            throw new RuntimeException();
        }

        doctor.setHospId(hospital);

        try{
            doctor = doctorRepository.save(doctor);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        return doctor;
    }
}
