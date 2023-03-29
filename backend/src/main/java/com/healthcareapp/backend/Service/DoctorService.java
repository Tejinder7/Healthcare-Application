package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.DoctorRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;


    public Doctor getDoctorByDocId(int docId){
        Doctor doc = doctorRepository.findDoctorByDocId(docId);
        if(doc == null){
            throw new RuntimeException();
        }
        return doc;
    }

    public Hospital getHospitalByDocId(int docId){
        Doctor doc = getDoctorByDocId(docId);
        Hospital hospital = doc.getHospId();
        if(hospital == null){
            throw new RuntimeException();
        }

        return hospital;
    }

    public Doctor addDoctor(String name, String licId, String phNum, String doctorSpec, int hospId){
        Doctor doc = new Doctor();

        Hospital hosp = hospitalRepository.getHospitalsByHospId(hospId);

        if(hosp == null){
            throw new RuntimeException();
        }

        doc.setName(name);
        doc.setLicId(licId);
        doc.setPhoneNum(phNum);
        doc.setDocSpecialization(doctorSpec);
        doc.setHospId(hosp);

        try {
            doc = doctorRepository.save(doc);
        }
        catch (Exception e){
            throw new RuntimeException();
        }

        return doc;
    }

}
