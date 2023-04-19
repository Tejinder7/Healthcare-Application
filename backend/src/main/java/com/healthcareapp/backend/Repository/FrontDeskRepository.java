package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FrontDeskRepository extends JpaRepository<FrontDesk, Integer> {

    public List<FrontDesk> findByHospital(Hospital hospital);
    public Optional<FrontDesk> findByUsername(String userId);

}
