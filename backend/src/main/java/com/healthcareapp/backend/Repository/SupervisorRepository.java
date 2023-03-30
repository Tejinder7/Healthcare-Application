package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    public Supervisor findSupervisorByAuthId(int authId);

    public Supervisor findByAddress(String address);
}
