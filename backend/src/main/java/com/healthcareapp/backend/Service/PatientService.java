package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.PatientRepository;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient){
        patientRepository.save(patient);
        return patient;
    }

    public Patient getPatientById(int Pid){
        Patient patient = patientRepository.findPatientByPatientId(Pid);
        if(patient == null){
            throw new RuntimeException();
        }
        return patient;
    }
}
