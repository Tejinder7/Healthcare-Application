package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.EncounterDao;
import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Encounter;
import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncounterServices {

    @Autowired
    private MedicalHistoryServices medicalHistoryServices;
    @Autowired
    private DoctorServices doctorServices;
    @Autowired
    private PatientServices patientServices;
    @Autowired
    private EncounterDao encounterDao;

    public Encounter addEncounter(int patientId, int docId){
        Encounter encounter = new Encounter();

        Patient patient = patientServices.getPatientById(patientId);
        encounter.setPatientId(patient);

        Doctor doctor = doctorServices.getDoctorByDocId(docId);
        encounter.setDoctorId(doctor);

        medicalHistoryServices.addMedicalHistory(patientId);
        MedicalHistory mh = medicalHistoryServices.getMedicalHistoryByPatientId(patientId);
        encounter.setMedicalHistoryId(mh);

        encounterDao.save(encounter);

        return encounter;
    }

    public MedicalHistory saveEncounter(String pres, String symptoms, int patientId){
        MedicalHistory mh = medicalHistoryServices.updateMedicalHistory(pres, symptoms, patientId);
        return mh;
    }

}
