package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.MedicalHistoryRepository;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PatientService patientServices;

    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId){
        Patient patient = patientServices.getPatientById(patientId);
        List<MedicalHistory> mhList = medicalHistoryRepository.findMedicalHistoriesByPatientId(patient);
        if(mhList.size() == 0){
            throw new RuntimeException();
        }
        return mhList;
    }

    public MedicalHistory addMedicalHistory(Patient patient, Encounter encounter){
        MedicalHistory mh = new MedicalHistory();
        mh.setPatientId(patient);
        mh.setEncounterId(encounter);
        medicalHistoryRepository.save(mh);
        return mh;
    }

    public MedicalHistory getMedicalHistoryByEncounter(Encounter encounterId){
        MedicalHistory mh = medicalHistoryRepository.findMedicalHistoryByEncounterId(encounterId);
        if(mh == null){
            throw new RuntimeException();
        }
        return mh;
    }

    public MedicalHistory updateMedicalHistory(String pres, String symptoms, Encounter encounterId){
        MedicalHistory mh = getMedicalHistoryByEncounter(encounterId);
        try {
            mh.setPrescription(pres);
            mh.setSymptoms(symptoms);
            medicalHistoryRepository.save(mh);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return mh;
    }
}
