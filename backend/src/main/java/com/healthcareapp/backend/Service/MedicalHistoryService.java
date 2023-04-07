package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.MedicalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalHistoryService {
    private MedicalHistoryRepository medicalHistoryRepository;
    private PatientService patientService;

    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository, PatientService patientService) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientService = patientService;
    }
    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId) throws RuntimeException{
        Patient patient = patientService.getPatientById(patientId);
        List<MedicalHistory> medicalHistoryList = medicalHistoryRepository.findByPatient(patient);

        return medicalHistoryList;
    }
    public MedicalHistory addMedicalHistory(Patient patient, Encounter encounter, String prescription, String symptoms) throws RuntimeException{
        MedicalHistory medicalHistory = new MedicalHistory();

        medicalHistory.setPatient(patient);
        medicalHistory.setEncounter(encounter);
        medicalHistory.setPrescription(prescription);
        medicalHistory.setSymptoms(symptoms);

        medicalHistoryRepository.save(medicalHistory);
        return medicalHistory;
    }
    public MedicalHistory getMedicalHistoryByEncounter(Encounter encounterId){
        MedicalHistory medicalHistory = medicalHistoryRepository.findMedicalHistoryByEncounter(encounterId);
        if(medicalHistory == null){
            throw new RuntimeException();
        }
        return medicalHistory;
    }
}
