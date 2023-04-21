package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ForbiddenException;
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
    private FrontDeskService frontDeskService;
    private DoctorService doctorService;

    public PendingQueueService(PendingQueueRepository pendingQueueRepository, PatientService patientService, FrontDeskService frontDeskService, DoctorService doctorService) {
        this.pendingQueueRepository = pendingQueueRepository;
        this.patientService = patientService;
        this.frontDeskService = frontDeskService;
        this.doctorService = doctorService;
    }

    public PendingQueue addPendingQueue(String userId, int pid) throws RuntimeException{

        PendingQueue pendingQueue = new PendingQueue();

        Patient patient = patientService.getPatientById(pid);
        pendingQueue.setPatient(patient);

        FrontDesk frontDesk = frontDeskService.getFrontDeskByUserId(userId);

        pendingQueue.setHospital(frontDesk.getHospital());

        try {
            String date = java.time.LocalDate.now().toString();
            String time = java.time.LocalTime.now().toString();
            String dateTime = date + " " + time;
            pendingQueue.setDateTime(dateTime);
        }
        catch (DateTimeException exception){
            throw new RuntimeException(exception);
        }
        try{
            pendingQueueRepository.save(pendingQueue);
        }catch(Exception exception){
            throw new ForbiddenException("Appointment already exists for this patient");
        }
        return pendingQueue;
    }

    public List<PendingQueue> getPendingQueueByDocId(String doctorUserId) throws RuntimeException{

        Doctor doctor = doctorService.getDoctorByUserId(doctorUserId);

        Hospital hospital = doctor.getHospital();

        List<PendingQueue> pendingQueueList = pendingQueueRepository.findByHospital(hospital);

        return pendingQueueList;
    }

    public void deletePendingQueue(Patient patient) throws RuntimeException{
        Optional<PendingQueue> pendingQueue = pendingQueueRepository.findByPatient(patient);

        if(pendingQueue.isEmpty()){
            throw new ResourceNotFoundException("No entry for patient with id: "+ patient.getPatientId()+ " in pending queue");
        }
        pendingQueueRepository.deleteById(pendingQueue.get().getPendingQueueId());
    }

}
