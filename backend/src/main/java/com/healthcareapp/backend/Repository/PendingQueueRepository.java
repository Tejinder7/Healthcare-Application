package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.PendingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingQueueRepository extends JpaRepository<PendingQueue, Integer> {

    public List<PendingQueue> findPendingQueueByHospId(Hospital hospital);

}
