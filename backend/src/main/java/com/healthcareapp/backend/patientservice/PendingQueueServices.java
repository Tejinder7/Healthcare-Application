package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.PendingQueueDao;
import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PendingQueueServices {

    @Autowired
    private PendingQueueDao pendingQueueDao;

    public PendingQueue addPendingQueue(PendingQueue pq){
        PendingQueue c = pendingQueueDao.save(pq);
        return c;
    }

}
