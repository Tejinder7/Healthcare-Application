package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.data.repository.CrudRepository;

public interface PendingQueueDao extends CrudRepository<PendingQueue, Integer> {

}
