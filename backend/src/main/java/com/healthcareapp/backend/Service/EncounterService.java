package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.EncounterRepository;
import org.springframework.stereotype.Component;

@Component
public class EncounterService {
    private MedicalHistoryService medicalHistoryService;
    private DoctorService doctorServices;
    private PatientService patientServices;
    private EncounterRepository encounterRepository;

    public EncounterService(MedicalHistoryService medicalHistoryService, DoctorService doctorServices, PatientService patientServices, EncounterRepository encounterRepository) {
        this.medicalHistoryService = medicalHistoryService;
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
        this.encounterRepository = encounterRepository;
    }

    public Encounter addEncounter(int patientId, int authId){
        Encounter encounter = new Encounter();

        Patient patient = patientServices.getPatientById(patientId);
        encounter.setPatient(patient);

        Doctor doctor = doctorServices.getDoctorByAuthId(authId);
        encounter.setDoctor(doctor);

        encounterRepository.save(encounter);

        return encounter;
    }
    public MedicalHistory saveEncounter(String prescription, String symptoms, int encounterId){
        Encounter encounter = getEncounterById(encounterId);
        Patient patient = encounter.getPatient();
        MedicalHistory medicalHistory = medicalHistoryService.addMedicalHistory(patient,encounter);
        encounter.setMedicalHistory(medicalHistory);
        encounterRepository.save(encounter);

        MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(prescription, symptoms, encounter);
        return updatedMedicalHistory;
    }

    public Encounter getEncounterById(int encounterId){
        Encounter encounter = encounterRepository.getEncounterByEncounterId(encounterId);
        if(encounter == null){
            throw new RuntimeException();
        }
        return encounter;
    }
}
