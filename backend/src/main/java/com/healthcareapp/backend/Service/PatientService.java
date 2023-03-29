package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientService {
    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
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