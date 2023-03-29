package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.EncounterRepository;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncounterService {

    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private DoctorService doctorServices;
    @Autowired
    private PatientService patientServices;
    @Autowired
    private EncounterRepository encounterRepository;

    public Encounter addEncounter(int patientId, int docId){
        Encounter encounter = new Encounter();

        Patient patient = patientServices.getPatientById(patientId);
        encounter.setPatientId(patient);

        Doctor doctor = doctorServices.getDoctorByDocId(docId);
        encounter.setDoctorId(doctor);

        encounterRepository.save(encounter);

        return encounter;
    }

    public MedicalHistory saveEncounter(String pres, String symptoms, int encounterId){
        Encounter encounter = getEncounterById(encounterId);
        Patient patient = encounter.getPatientId();
        MedicalHistory medicalHistory = medicalHistoryService.addMedicalHistory(patient,encounter);
        encounter.setMedicalHistoryId(medicalHistory);
        encounterRepository.save(encounter);
        MedicalHistory mh = medicalHistoryService.updateMedicalHistory(pres, symptoms, encounter);
        return mh;
    }

    public Encounter getEncounterById(int encounterId){
        Encounter encounter = encounterRepository.getEncounterByEncounterId(encounterId);
        if(encounter == null){
            throw new RuntimeException();
        }
        return encounter;
    }
}
