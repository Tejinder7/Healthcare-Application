package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Patient> patient = patientRepository.findById(Pid);
        if(patient.isEmpty()){
            throw new ResourceNotFoundException("No patient with id: "+ Pid+ " found");
        }

        return patient.get();
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
