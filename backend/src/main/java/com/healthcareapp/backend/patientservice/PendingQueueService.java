package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.PendingQueueDao;
import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PendingQueueService {

    @Autowired
    private PendingQueueDao pqDao;

    @Autowired
    private DoctorService doctorService;

    public List<PendingQueue> getPendingQueuebyDid(int id){
        Hospital h1 = doctorService.findHospitalfromDoctor(id);

        List<PendingQueue> list = pqDao.findByHospital(h1);

        return list;
    }
}
