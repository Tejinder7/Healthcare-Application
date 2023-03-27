package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.DoctorDao;
import com.healthcareapp.backend.dao.HospitalDao;
import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorServices {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private HospitalDao hospitalDao;

    public Doctor getDoctorByDocId(int docId){
        Doctor doc = doctorDao.findDoctorByDocId(docId);
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

        Hospital hosp = hospitalDao.getHospitalsByHospId(hospId);

        if(hosp == null){
            throw new RuntimeException();
        }

        doc.setName(name);
        doc.setLicId(licId);
        doc.setPhoneNum(phNum);
        doc.setDocSpecialization(doctorSpec);
        doc.setHospId(hosp);

        try {
            doc = doctorDao.save(doc);
        }
        catch (Exception e){
            throw new RuntimeException();
        }

        return doc;
    }

}