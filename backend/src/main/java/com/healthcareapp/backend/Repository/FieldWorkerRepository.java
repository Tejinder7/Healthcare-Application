package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FieldWorkerRepository extends JpaRepository<FieldWorker, Integer> {

    public List<FieldWorker> findFieldWorkerBySupervisor(Supervisor supervisor);

    public FieldWorker findFieldWorkerByAuthId(int fieldWorkerAuthId);

}
