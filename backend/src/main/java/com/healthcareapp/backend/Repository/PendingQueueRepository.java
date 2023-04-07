package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.PendingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PendingQueueRepository extends JpaRepository<PendingQueue, Integer> {

    public List<PendingQueue> findPendingQueueByHospital(Hospital hospital);

    public Optional<PendingQueue> findByPatient(Patient patient);

}
