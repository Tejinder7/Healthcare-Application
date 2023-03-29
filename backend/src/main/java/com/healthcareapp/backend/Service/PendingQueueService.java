package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.PendingQueueRepository;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.PendingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.List;

@Component
public class PendingQueueService {

    @Autowired
    private PendingQueueRepository pendingQueueRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorService doctorServices;

    public PendingQueue addPendingQueue(int hospId, int pid){

        PendingQueue pq = new PendingQueue();

        Patient patient = patientService.getPatientById(pid);
        pq.setPatientId(patient);

        Hospital hospital = hospitalService.getHospitalById(hospId);
        pq.setHospId(hospital);

        try {
            String date = java.time.LocalDate.now().toString();
            String time = java.time.LocalTime.now().toString();
            String dateTime = date + " " + time;
            pq.setDateTime(dateTime);
        }
        catch (DateTimeException e){
            throw new RuntimeException();
        }

        pendingQueueRepository.save(pq);
        return pq;
    }

    public List<PendingQueue> getPendingQueueByDocId(int docId){
        Hospital hospital = doctorServices.getHospitalByDocId(docId);

        List<PendingQueue> pqList = pendingQueueRepository.findPendingQueueByHospId(hospital);

        if(pqList.size() == 0){
            throw new RuntimeException();
        }

        return pqList;
    }

}
