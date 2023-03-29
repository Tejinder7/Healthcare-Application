package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    public Supervisor findSupervisorBySupId(int supId);
}
