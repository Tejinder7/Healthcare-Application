package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.PatientDao;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientServices {

    @Autowired
    private PatientDao patientDao;

    public Patient addPatient(Patient patient){
        patientDao.save(patient);
        return patient;
    }

    public Patient getPatientById(int Pid){
        Patient patient = patientDao.findPatientByPatientId(Pid);
        if(patient == null){
            throw new RuntimeException();
        }
        return patient;
    }
}
