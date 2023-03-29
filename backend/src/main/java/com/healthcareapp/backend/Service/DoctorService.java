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


    public Doctor addDoctor(String name, String licId, String phNum, String doctorSpec, int hospId, String userId, String password){
        Doctor doc = new Doctor();

        Hospital hosp = hospitalRepository.getHospitalsByHospId(hospId);


        if(hospital == null){
            throw new RuntimeException();
        }

        doc.setName(name);
        doc.setLicId(licId);
        doc.setPhoneNum(phNum);
        doc.setDocSpecialization(doctorSpec);
        doc.setHospId(hosp);
        doc.setUserId(userId);
        doc.setPassword(password);
        doc.setUserType("Doctor");


        try{
            doctor = doctorRepository.save(doctor);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        return doctor;
    }
}
