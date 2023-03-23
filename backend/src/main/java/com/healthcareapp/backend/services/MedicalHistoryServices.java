package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.MedicalHistoryDao;
import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryServices {

    @Autowired
    private MedicalHistoryDao medicalHistoryDao;

    @Autowired
    private PatientServices patientServices;

    public MedicalHistory getMedicalHistoryByPatientId(int patientId){
        Patient patient = patientServices.getPatientById(patientId);
        MedicalHistory mh = medicalHistoryDao.findMedicalHistoriesByPatientId(patient);
        if(mh == null){
            throw new RuntimeException();
        }
        return mh;
    }

    public MedicalHistory addMedicalHistory(int patientId){
        MedicalHistory mh = new MedicalHistory();
        Patient patient = patientServices.getPatientById(patientId);
        mh.setPatientId(patient);
        medicalHistoryDao.save(mh);
        return mh;
    }

    public MedicalHistory updateMedicalHistory(String pres, String symptoms, int patientId){
        MedicalHistory mh = getMedicalHistoryByPatientId(patientId);
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
