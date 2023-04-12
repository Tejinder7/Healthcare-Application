package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    public Supervisor findSupervisorByAuthId(int authId);

    public Optional<Supervisor> findByPincode(int address);

    public Optional<Supervisor> findByUserId(String userId);
}
