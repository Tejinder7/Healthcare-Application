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
    private EncounterRepository encounterRepository;
    private DoctorService doctorService;
    private PatientService patientService;

    private PendingQueueService pendingQueueService;

    public EncounterService(DoctorService doctorService, PatientService patientService, PendingQueueService pendingQueueService, EncounterRepository encounterRepository) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pendingQueueService = pendingQueueService;
        this.encounterRepository = encounterRepository;
    }

    public Encounter addEncounter(int patientId, String doctorUserId) throws RuntimeException{
        Encounter encounter = new Encounter();

        Patient patient= patientService.getPatientById(patientId);

        Doctor doctor = doctorService.getDoctorByUserId(doctorUserId);

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
            throw new ResourceNotFoundException("Encounter with id: "+ encounter.getEncounterId()+" not found");
        }

        updatedEncounter.get().setPrescription(encounter.getPrescription());
        updatedEncounter.get().setSymptoms(encounter.getSymptoms());
        updatedEncounter.get().setFlag(true);

        encounterRepository.save(updatedEncounter.get());

        return updatedEncounter.get();
    }

//    public Encounter getEncounterById(int encounterId){
//        Optional<Encounter> encounter = encounterRepository.findById(encounterId);
//
//        if(encounter.isEmpty()){
//            throw new ResourceNotFoundException("No encounter with id: "+ encounterId+ " found");
//        }
//
//        return encounter.get();
//    }

    public List<Encounter> getEncountersByDoctor(Doctor doctor){
        List<Encounter> encounterList;
        encounterList= encounterRepository.findByDoctor(doctor);

        return encounterList;
    }

    public List<Encounter> getEncountersByPatientId(int patientId) throws RuntimeException{
        Patient patient= patientService.getPatientById(patientId);

        List<Encounter> encounterList= encounterRepository.findByPatient(patient);

        return encounterList;
    }

    public List<Encounter> getUnsavedEncounters(String doctorId){
        Doctor doctor = doctorService.getDoctorByUserId(doctorId);

        List<Encounter> encounterList = encounterRepository.findByDoctorAndFlagIsFalse(doctor);

        return encounterList;
    }
}
