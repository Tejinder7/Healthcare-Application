package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientService {
    private PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public Patient addPatient(Patient patient){
        Patient patient1;
        String patientName = patient.getName().toLowerCase();
        patient.setName(patientName);
        try{
            patient1 = patientRepository.save(patient);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return patient1;
    }
    public Patient getPatientById(int Pid){
        Patient patient = patientRepository.findPatientByPatientId(Pid);
        if(patient == null){
            throw new RuntimeException();
        }
        return patient;
    }

    public List<Patient> getPatientsByName(String name){
        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientsWithGivenNameList = new ArrayList<>();
        try {
            for (int i = 0; i < patientList.size(); i++) {
                if (patientList.get(i).getName().contains(name)) {
                    patientsWithGivenNameList.add(patientList.get(i));
                }
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return patientsWithGivenNameList;
    }

    public Patient getPatientByFieldWorker(FieldWorker fieldWorker){
        Patient patient = patientRepository.findPatientByFieldWorker(fieldWorker);
        return patient;
    }
}
