package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FollowUpService {
    private FollowUpRepository followUpRepository;
    private PatientRepository patientRepository;
    private SupervisorService supervisorService;
    private FieldWorkerService fieldWorkerService;
    private DoctorService doctorService;

    public FollowUpService(FollowUpRepository followUpRepository, SupervisorService supervisorService, FieldWorkerService fieldWorkerService, PatientRepository patientRepository, DoctorService doctorService) {
        this.followUpRepository = followUpRepository;
        this.supervisorService = supervisorService;
        this.fieldWorkerService = fieldWorkerService;
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
    }

    public List<FollowUp> getCurrentDateFollowUps(String date, int fieldWorkerAuthId){
        FieldWorker fieldWorker;

        fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerAuthId);

        List<Patient> patientList = fieldWorker.getPatientList();

        List<FollowUp> followUpList= new ArrayList<FollowUp>();

//        patientList.forEach(patient -> followUpRepository.findByDateAndPatient(date, patient).forEach(followUp -> followUpList.add(followUp)));

        patientList.forEach(patient -> {followUpList.addAll(followUpRepository.findByDateAndPatient(date, patient));});

        return followUpList;
    }


    public FollowUp updateFollowUp(FollowUp followUp) throws RuntimeException{

//        int followUpId = followUp.getFollowUpId();

        Optional<FollowUp> updatedFollowUp = followUpRepository.findById(followUp.getFollowUpId());

        if(updatedFollowUp.isEmpty())
        {
            throw new ResourceNotFoundException("Follow up with id: "+ followUp.getFollowUpId()+ " not found");
        }

        updatedFollowUp.get().setFlag(followUp.isFlag());
        updatedFollowUp.get().setLastSyncDate(followUp.getLastSyncDate());
        updatedFollowUp.get().setFieldWorkerRemarks(followUp.getFieldWorkerRemarks());

        followUpRepository.save(updatedFollowUp.get());
        return updatedFollowUp.get();
    }

    public List<FollowUp> getAllFollowUpsUnderSupervisor(String userId){
        Supervisor supervisor = supervisorService.getSupervisorByUserId(userId);

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = supervisor.getHospitalList();

//        hospitalList.forEach(hospital -> followUpRepository.findByHospitalAndFlagIsFalse(hospital).forEach(followUp -> followUpList.add(followUp)));

        hospitalList.forEach(hospital -> {followUpList.addAll(followUpRepository.findByHospitalAndFlagIsFalse(hospital));});

        return followUpList;
    }

    public void addFollowUps(Encounter encounter){
        List<FollowUp> followUpList = encounter.getFollowUpList();

        for (FollowUp followUp : followUpList) {
            followUp.setEncounter(encounter);
            followUp.setFlag(false);
            followUp.setPatient(encounter.getPatient());
            followUp.setHospital(encounter.getDoctor().getHospital());
            followUpRepository.save(followUp);
        }
    }

    public List<FollowUp> getFollowUpsByFieldWorker(int fieldWorkerId){

        FieldWorker fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerId);
        List<Patient> patientList = patientRepository.findByFieldWorker(fieldWorker);

        List<FollowUp> followUpList = new ArrayList<>();

        for(int i=0; i<patientList.size(); i++){
            List<FollowUp> followUpListForPatient = followUpRepository.findByPatient(patientList.get(i));
            followUpList.addAll(followUpListForPatient);
        }
        return followUpList;
    }

    public List<FollowUp> getAllFollowUpsAssignedByDoctor(String doctorUserId) throws RuntimeException{
        Doctor doctor= doctorService.getDoctorByUserId(doctorUserId);

        List<Encounter> encounterList= doctor.getEncounterList();

        List<FollowUp> followUpList= new ArrayList<>();

        encounterList.forEach(encounter -> followUpList.addAll(encounter.getFollowUpList()));

        return followUpList;
    }
}
