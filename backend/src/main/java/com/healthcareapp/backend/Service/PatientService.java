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
    public Patient addPatient(Patient patient) throws RuntimeException{
        Patient savedPatient;
        String patientName = patient.getName().toLowerCase();
        patient.setName(patientName);

        savedPatient = patientRepository.save(patient);

        return savedPatient;
    }
    public Patient getPatientById(int Pid){
        Optional<Patient> patient = patientRepository.findById(Pid);

        if(patient.isEmpty()){
            throw new ResourceNotFoundException("No patient with id: "+ Pid+ " found");
        }
        return patient.get();
    }

    public List<Patient> getPatientsByName(String name) throws RuntimeException{
        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientsWithGivenNameList = new ArrayList<>();

        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getName().contains(name)) {
                patientsWithGivenNameList.add(patientList.get(i));
            }
        }

        if(patientsWithGivenNameList.isEmpty()){
            throw new ResourceNotFoundException("No patients with name: "+ name+ " found");
        }

        return patientsWithGivenNameList;
    }

    public List<Patient> getPatientByFieldWorker(FieldWorker fieldWorker){
        List<Patient> patientList;
        patientList = patientRepository.findPatientByFieldWorker(fieldWorker);
        if(patientList.size() == 0)
            return new ArrayList<Patient>();
        return patientList;
    }

    public Patient updatePatient(Patient patient){
        patientRepository.save(patient);
        return patient;
    }
}
