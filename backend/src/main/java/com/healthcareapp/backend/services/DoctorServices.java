package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.DoctorDao;
import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorServices {

    @Autowired
    private DoctorDao doctorDao;

    public Hospital getHospitalByDocId(int docId){
        Doctor doc = doctorDao.findDoctorByDocId(docId);
        if(doc == null){
            throw new RuntimeException();
        }
        Hospital hospital = doc.getHospId();
        if(hospital == null){
            throw new RuntimeException();
        }

        return hospital;
    }

}
