package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.EncounterRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EncounterService {
    private MedicalHistoryService medicalHistoryService;
    private DoctorService doctorServices;
    private PatientService patientServices;

    private PendingQueueService pendingQueueService;
    private EncounterRepository encounterRepository;

    public EncounterService(MedicalHistoryService medicalHistoryService, DoctorService doctorServices, PatientService patientServices, EncounterRepository encounterRepository, PendingQueueService pendingQueueService) {
        this.medicalHistoryService = medicalHistoryService;
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
        this.encounterRepository = encounterRepository;
        this.pendingQueueService = pendingQueueService;
    }

    public Encounter addEncounter(int patientId, int authId) throws RuntimeException{
        Encounter encounter = new Encounter();

        Patient patient= patientServices.getPatientById(patientId);

        Doctor doctor = doctorServices.getDoctorByAuthId(authId);

        encounter.setPatient(patient);
        encounter.setDoctor(doctor);

        pendingQueueService.deletePendingQueue(patient);

        encounter.setFlag(false);

        encounterRepository.save(encounter);

        return encounter;
    }
    public MedicalHistory saveEncounter(String prescription, String symptoms, int encounterId) throws RuntimeException{
        Encounter encounter = getEncounterById(encounterId);

        Patient patient = encounter.getPatient();
        MedicalHistory medicalHistory = medicalHistoryService.addMedicalHistory(patient, encounter, prescription, symptoms);

        encounter.setMedicalHistory(medicalHistory);
        encounter.setFlag(true);

        encounterRepository.save(encounter);

        return medicalHistory;
    }

    public Encounter getEncounterById(int encounterId){
        Optional<Encounter> encounter = encounterRepository.findById(encounterId);

        if(encounter.isEmpty()){
            throw new ResourceNotFoundException("No encounter with id: "+ encounterId+ " found");
        }

        return encounter.get();
    }
}
