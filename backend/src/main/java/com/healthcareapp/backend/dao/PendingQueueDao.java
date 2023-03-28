package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingQueueDao extends JpaRepository<PendingQueue, Integer> {

    public List<PendingQueue> findPendingQueueByHospId(Hospital hospital);

}
