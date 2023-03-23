package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.MedicalHistoryDao;
import com.healthcareapp.backend.entities.Encounter;
import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalHistoryServices {

    @Autowired
    private MedicalHistoryDao medicalHistoryDao;

    @Autowired
    private PatientServices patientServices;

    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId){
        Patient patient = patientServices.getPatientById(patientId);
        List<MedicalHistory> mhList = medicalHistoryDao.findMedicalHistoriesByPatientId(patient);
        if(mhList.size() == 0){
            throw new RuntimeException();
        }
        return mhList;
    }

    public MedicalHistory addMedicalHistory(Patient patient, Encounter encounter){
        MedicalHistory mh = new MedicalHistory();
        mh.setPatientId(patient);
        mh.setEncounterId(encounter);
        medicalHistoryDao.save(mh);
        return mh;
    }

    public MedicalHistory getMedicalHistoryByEncounter(Encounter encounterId){
        MedicalHistory mh = medicalHistoryDao.findMedicalHistoryByEncounterId(encounterId);
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
            medicalHistoryDao.save(mh);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return mh;
    }
}
