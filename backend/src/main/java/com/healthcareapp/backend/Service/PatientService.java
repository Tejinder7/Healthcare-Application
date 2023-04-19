package com.healthcareapp.backend.Service;


import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PatientService {
    private PatientRepository patientRepository;
    private FieldWorkerRepository fieldWorkerRepository;

    public PatientService(PatientRepository patientRepository, FieldWorkerRepository fieldWorkerRepository) {
        this.patientRepository = patientRepository;
        this.fieldWorkerRepository = fieldWorkerRepository;
    }

    public Patient addPatient(Patient patient) throws RuntimeException{
        Patient savedPatient;

        if(patient.getDOB() != null) {

            LocalDate birthDate = LocalDate.parse(patient.getDOB());

            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();

            patient.setDOB(patient.getDOB());
            patient.setAge(age);
        }

        savedPatient = patientRepository.save(patient);

        return savedPatient;
    }
    public Patient getPatientById(int Pid){
        Optional<Patient> patient = patientRepository.findById(Pid);

        if(patient.isEmpty()){
            throw new ResourceNotFoundException("Patient with id: "+ Pid+ " not found");
        }
        return patient.get();
    }

    public List<Patient> getPatientsByName(String name) throws RuntimeException{
        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientsWithGivenNameList = new ArrayList<>();

        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                patientsWithGivenNameList.add(patientList.get(i));
            }
        }

        return patientsWithGivenNameList;
    }

    public Patient updatePatient(Patient patient){
        patientRepository.save(patient);
        return patient;
    }

    public List<Patient> getPatientByFieldWorker(int fieldWorkerId){
        Optional<FieldWorker> fieldWorker = fieldWorkerRepository.findById(fieldWorkerId);
        if(fieldWorker.isEmpty()){
            throw new RuntimeException("Field Worker with id: "+ fieldWorkerId+ " not found");
        }
        List<Patient> patientList = patientRepository.findByFieldWorker(fieldWorker.get());
        return patientList;
    }
}
