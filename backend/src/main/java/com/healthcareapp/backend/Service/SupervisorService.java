package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;
@Component
public class SupervisorService {
    SupervisorRepository supervisorRepository;

    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public Supervisor addSupervisor(Supervisor supervisor){
        try{
            supervisor.setUserType("Supervisor");
            supervisorRepository.save(supervisor);
            return supervisor;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}

