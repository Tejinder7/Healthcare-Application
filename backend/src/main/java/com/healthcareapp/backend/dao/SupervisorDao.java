package com.healthcareapp.backend.dao;

import com.healthcareapp.backend.entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorDao extends JpaRepository<Supervisor, Integer> {
    public Supervisor findSupervisorBySupId(int supId);
}
