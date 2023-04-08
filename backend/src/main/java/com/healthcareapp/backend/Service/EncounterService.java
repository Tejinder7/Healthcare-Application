package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Repository.EncounterRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EncounterService {
    private DoctorService doctorServices;
    private PatientService patientServices;

    private PendingQueueService pendingQueueService;
    private EncounterRepository encounterRepository;

    public EncounterService(DoctorService doctorServices, PatientService patientServices, PendingQueueService pendingQueueService, EncounterRepository encounterRepository) {
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
        this.pendingQueueService = pendingQueueService;
        this.encounterRepository = encounterRepository;
    }

    public Encounter addEncounter(int patientId, int doctorId) throws RuntimeException{
        Encounter encounter = new Encounter();

        Patient patient= patientServices.getPatientById(patientId);

        Doctor doctor = doctorServices.getDoctorByAuthId(doctorId);

        encounter.setPatient(patient);
        encounter.setDoctor(doctor);

        pendingQueueService.deletePendingQueue(patient);

        encounter.setFlag(false);

        encounterRepository.save(encounter);

        return encounter;
    }

    public Encounter updateEncounter(Encounter encounter) throws RuntimeException{
        Optional<Encounter> updatedEncounter= encounterRepository.findById(encounter.getEncounterId());

        if(updatedEncounter.isEmpty()){
            throw new ResourceNotFoundException("No encounter with id: "+ encounter.getEncounterId()+" found");
        }

        encounterRepository.save(encounter);

        return encounter;
    }

    public Encounter getEncounterById(int encounterId){
        Optional<Encounter> encounter = encounterRepository.findById(encounterId);

        if(encounter.isEmpty()){
            throw new ResourceNotFoundException("No encounter with id: "+ encounterId+ " found");
        }

        return encounter.get();
    }

    public List<Encounter> getEncounterByDoctor(Doctor doctor){
        List<Encounter> encounterList;
        encounterList= encounterRepository.findByDoctor(doctor);

        if(encounterList.isEmpty()){
            throw new ResourceNotFoundException("No encounters for the DoctorId: "+ doctor.getAuthId()+ " found");
        }

        return encounterList;
    }

    public List<Encounter> getEncountersByPatientId(int patientId) throws RuntimeException{
        Patient patient= patientServices.getPatientById(patientId);

        List<Encounter> encounterList= encounterRepository.findByPatient(patient);

        if(encounterList.isEmpty()){
            throw new ResourceNotFoundException("No Medical History corresponding to the patient with id: "+ patientId+ " found");
        }

        return encounterList;
    }
}
