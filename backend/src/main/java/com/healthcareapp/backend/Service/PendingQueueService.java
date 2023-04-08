package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.*;
import com.healthcareapp.backend.Repository.PendingQueueRepository;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;

@Component
public class PendingQueueService {
    private PendingQueueRepository pendingQueueRepository;
    private PatientService patientService;
    private HospitalService hospitalService;

    private FrontDeskService frontDeskService;
    private DoctorService doctorService;

    public PendingQueueService(FrontDeskService frontDeskService, PendingQueueRepository pendingQueueRepository, PatientService patientService, HospitalService hospitalService, DoctorService doctorService) {
        this.frontDeskService = frontDeskService;
        this.pendingQueueRepository = pendingQueueRepository;
        this.patientService = patientService;
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
    }

    public PendingQueue addPendingQueue(String userId, int pid){

        PendingQueue pendingQueue = new PendingQueue();

        Patient patient = patientService.getPatientById(pid);
        pendingQueue.setPatient(patient);

        FrontDesk frontDesk = frontDeskService.getFrontDeskByUserId(userId);

        Hospital hospital = hospitalService.getHospitalById(frontDesk.getHospital().getHospId());
        pendingQueue.setHospital(hospital);

        try {
            String date = java.time.LocalDate.now().toString();
            String time = java.time.LocalTime.now().toString();
            String dateTime = date + " " + time;
            pendingQueue.setDateTime(dateTime);
        }
        catch (DateTimeException e){
            throw new RuntimeException();
        }

        pendingQueueRepository.save(pendingQueue);
        return pendingQueue;
    }

    public List<PendingQueue> getPendingQueueByDocId(String userId){

        FrontDesk frontDesk = frontDeskService.getFrontDeskByUserId(userId);

        Hospital hospital = hospitalService.getHospitalById(frontDesk.getHospital().getHospId());

        List<PendingQueue> pendingQueueList = pendingQueueRepository.findPendingQueueByHospital(hospital);

        if(pendingQueueList.size() == 0){
            throw new RuntimeException();
        }

        return pendingQueueList;
    }

    public void deletePendingQueue(Patient patient){
        Optional<PendingQueue> pendingQueue = pendingQueueRepository.findByPatient(patient);

        if(pendingQueue.isEmpty()){
            throw new ResourceNotFoundException("No entry for patient with id: "+ patient.getPatientId()+ " in pending queue");
        }
        int pendingQueueId = pendingQueue.get().getPendingQueueId();
        pendingQueueRepository.deleteById(pendingQueueId);
    }

}
