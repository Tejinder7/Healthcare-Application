package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.FrontDeskRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FrontDeskService {
    private FrontDeskRepository frontDeskRepository;
    private HospitalService hospitalService;

    public FrontDeskService(FrontDeskRepository frontDeskRepository, HospitalService hospitalService) {
        this.frontDeskRepository = frontDeskRepository;
        this.hospitalService = hospitalService;
    }

    public FrontDesk addFrontDesk(FrontDesk frontDesk, int hospitalId) throws RuntimeException{

        Hospital hospital = hospitalService.getHospitalById(hospitalId);

        frontDesk.setHospital(hospital);
        frontDesk.setUserType("Front Desk");

        frontDeskRepository.save(frontDesk);
        return frontDesk;
    }

    public List<FrontDesk> getAllFrontDeskByHospital(Hospital hospital){
        List<FrontDesk> frontDeskList= frontDeskRepository.findByHospital(hospital);
        return frontDeskList;
    }

    public FrontDesk updateFrontDesk(FrontDesk frontDesk) throws RuntimeException{
        Optional<FrontDesk> updatedFrontDesk = frontDeskRepository.findById(frontDesk.getAuthId());

        if(updatedFrontDesk.isEmpty()){
            throw new ResourceNotFoundException("No Front Desk with id: "+ frontDesk.getAuthId()+ " found");
        }

        updatedFrontDesk.get().setName(frontDesk.getName());
        updatedFrontDesk.get().setUserId(frontDesk.getUserId());
        updatedFrontDesk.get().setPassword(frontDesk.getPassword());

        frontDeskRepository.save(updatedFrontDesk.get());

        return updatedFrontDesk.get();
    }
}
