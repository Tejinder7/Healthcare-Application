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
        Patient c = patientDao.save(patient);
        return c;
    }

    public Patient getPatientById(int id){
        Patient c = patientDao.findByPid(id);
        return c;
    }

}
