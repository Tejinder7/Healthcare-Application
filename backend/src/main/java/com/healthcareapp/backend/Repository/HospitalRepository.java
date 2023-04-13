package com.healthcareapp.backend.Repository;

import com.healthcareapp.backend.Model.Hospital;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
//    public Hospital findByHospId(int id);

    public List<Hospital> findByPincode(int pincode);

}

