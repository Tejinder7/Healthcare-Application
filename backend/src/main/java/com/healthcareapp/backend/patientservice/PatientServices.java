package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.PatientDao;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientServices {

    @Autowired
    private PatientDao patientDao;

    public Patient addPatient(Patient patient){
//        System.out.println(course);
        Patient c = patientDao.save(patient);
//        System.out.println(course);
        return c;
    }

}
