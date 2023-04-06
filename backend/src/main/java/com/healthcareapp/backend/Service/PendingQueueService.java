package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Repository.PendingQueueRepository;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.List;

@Component
public class PendingQueueService {
    private PendingQueueRepository pendingQueueRepository;
    private PatientService patientService;
    private HospitalService hospitalService;
    private DoctorService doctorService;

    public PendingQueueService(PendingQueueRepository pendingQueueRepository, PatientService patientService, HospitalService hospitalService, DoctorService doctorService) {
        this.pendingQueueRepository = pendingQueueRepository;
        this.patientService = patientService;
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
    }

    public PendingQueue addPendingQueue(int hospId, int pid){

        PendingQueue pendingQueue = new PendingQueue();

        Patient patient = patientService.getPatientById(pid);
        pendingQueue.setPatient(patient);

        Hospital hospital = hospitalService.getHospitalById(hospId);
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

    public List<PendingQueue> getPendingQueueByDocId(int hospId){
        Hospital hospital = hospitalService.getHospitalById(hospId);

        List<PendingQueue> pendingQueueList = pendingQueueRepository.findPendingQueueByHospital(hospital);

        if(pendingQueueList.size() == 0){
            throw new RuntimeException();
        }

        return pendingQueueList;
    }

    public void deletePendingQueue(Patient patient){
        PendingQueue pendingQueue = pendingQueueRepository.findPendingQueueByPatient(patient);
        int pendingQueueId = pendingQueue.getPendingQueueId();
        pendingQueueRepository.deleteById(pendingQueueId);
    }

}
