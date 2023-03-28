package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.FieldWorker;
import com.healthcareapp.backend.entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FieldWorkerDao extends JpaRepository<FieldWorker, Integer> {

    public List<FieldWorker> findBySupId(Supervisor supervisor);

    public FieldWorker findByFwId(int fwId);

}
