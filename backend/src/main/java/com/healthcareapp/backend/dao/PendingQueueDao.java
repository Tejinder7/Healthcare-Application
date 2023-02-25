package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PendingQueueDao extends CrudRepository<PendingQueue, Integer> {
    public List<PendingQueue> findByHospital(Hospital hospital);
}
