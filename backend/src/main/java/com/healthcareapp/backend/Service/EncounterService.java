package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.EncounterRepository;
import com.healthcareapp.backend.Validations.ValidationHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class EncounterService {
    private EncounterRepository encounterRepository;
    private DoctorService doctorService;
    private PatientService patientService;
    private ValidationHelper validationHelper;
    private PendingQueueService pendingQueueService;

    public EncounterService(DoctorService doctorService, PatientService patientService, PendingQueueService pendingQueueService, EncounterRepository encounterRepository, ValidationHelper validationHelper) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.pendingQueueService = pendingQueueService;
        this.encounterRepository = encounterRepository;
        this.validationHelper = validationHelper;
    }

    public Encounter addEncounter(int patientId, String doctorUserId) throws RuntimeException{

        validationHelper.usernamePasswordValidation(doctorUserId);

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

        validationHelper.strValidation(encounter.getPrescription());
        validationHelper.strValidation(encounter.getSymptoms());

        for(int i=0; i<encounter.getFollowUpList().size(); i++){
            FollowUp followUp = encounter.getFollowUpList().get(i);
            validationHelper.strValidation(followUp.getReadings().getBloodPressure());
            validationHelper.strValidation(followUp.getReadings().getSugar());
            validationHelper.strValidation(followUp.getReadings().getTemperature());
        }

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
    public List<Encounter> getEncountersByPatientId(int patientId) throws RuntimeException{
        Patient patient= patientService.getPatientById(patientId);

//        List<Encounter> encounterList= encounterRepository.findByPatient(patient);
        List<Encounter> encounterList = encounterRepository.findByPatientAndPrescriptionIsNotNullAndSymptomsIsNotNull(patient);

        return encounterList;
    }

    public List<Encounter> getUnsavedEncounters(String doctorId){
        Doctor doctor = doctorService.getDoctorByUserId(doctorId);

        List<Encounter> encounterList = encounterRepository.findByDoctorAndFlagIsFalse(doctor);

        return encounterList;
    }
}
