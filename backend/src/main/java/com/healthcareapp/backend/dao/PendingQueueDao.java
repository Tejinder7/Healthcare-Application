package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.PendingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingQueueDao extends JpaRepository<PendingQueue, Integer> {
}
