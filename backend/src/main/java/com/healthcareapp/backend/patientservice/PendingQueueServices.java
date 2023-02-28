package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.PendingQueueDao;
import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.List;

@Component
public class PendingQueueServices {

    @Autowired
    private PendingQueueDao pendingQueueDao;

    @Autowired
    private PendingQueueDao pqDao;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private HospitalServices hospitalServices;

    public List<PendingQueue> getPendingQueuebyDid(int id){
        Hospital h1 = doctorService.findHospitalfromDoctor(id);

        List<PendingQueue> list = pqDao.findByHospital(h1);

        if(list.size()==0)
            throw new RuntimeException();

        return list;
    }

    public PendingQueue addPendingQueue(PendingQueue pq){
        PendingQueue c = pendingQueueDao.save(pq);
        return c;
    }

    public PendingQueue setPendingQueueDateTime(PendingQueue pq){
        try {
            String date = java.time.LocalDate.now().toString();
            String time = java.time.LocalTime.now().toString();
            String date_time = date + " " + time;
            pq.setDate_time(date_time);
            return pq;
        }
        catch (DateTimeException e){
            throw new RuntimeException();
        }
    }

    public PendingQueue setPendingQueuePatient(PendingQueue pq, int p_id){
        Patient patient = this.patientServices.getPatientById(p_id);
        if(patient == null){
            throw new RuntimeException();
        }
        pq.setPatient(patient);
        return pq;
    }

    public PendingQueue setPendingQueueHospital(PendingQueue pq, int hosp_id){
        Hospital hospital = this.hospitalServices.getHospitalById(hosp_id);
        if(hospital == null){
            throw new RuntimeException();
        }
        pq.setHospital(hospital);
        return pq;
    }

}
