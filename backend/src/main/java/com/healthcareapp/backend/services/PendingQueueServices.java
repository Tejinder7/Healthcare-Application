package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.PendingQueueDao;
import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;

@Component
public class PendingQueueServices {

    @Autowired
    private PendingQueueDao pendingQueueDao;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private HospitalServices hospitalServices;

    public PendingQueue addPendingQueue(int hospId, int pid){

        PendingQueue pq = new PendingQueue();

        Patient patient = patientServices.getPatientById(pid);
        pq.setPatientId(patient);

        Hospital hospital = hospitalServices.getHospitalById(hospId);
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

        pendingQueueDao.save(pq);
        return pq;
    }



}
