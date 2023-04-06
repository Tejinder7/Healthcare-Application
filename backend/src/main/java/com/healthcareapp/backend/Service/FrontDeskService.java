package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.Doctor;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.FrontDeskRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FrontDeskService {
    private FrontDeskRepository frontDeskRepository;
    private HospitalRepository hospitalRepository;
    
    public FrontDeskService(FrontDeskRepository frontDeskRepository, HospitalRepository hospitalRepository) {
        this.frontDeskRepository = frontDeskRepository;
        this.hospitalRepository = hospitalRepository;
    }


    public FrontDesk addFrontDesk(FrontDesk frontDesk, int hospitalId){

        Hospital hospital;

        hospital = hospitalRepository.getHospitalsByHospId(hospitalId);

        if(hospital==null)
        {
            throw new RuntimeException();
        }

        frontDesk.setHospId(hospital);
        frontDesk.setUserType("Front Desk");


        try {
            frontDeskRepository.save(frontDesk);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return frontDesk;
    }

    public List<FrontDesk> getAllFrontDeskByHospital(Hospital hospital){
        List<FrontDesk> frontDeskList;
        try{
            frontDeskList = frontDeskRepository.getFrontDeskByHospital(hospital);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return frontDeskList;
    }

    public FrontDesk updateFrontDesk(FrontDesk frontDesk){
        FrontDesk frontDesk1 = frontDeskRepository.findFrontDeskByAuthId(frontDesk.getAuthId());
        frontDesk1.setName(frontDesk.getName());
        frontDesk1.setUserId(frontDesk.getUserId());
        frontDesk1.setPassword(frontDesk.getPassword());
        try {
            frontDeskRepository.save(frontDesk1);
            return frontDesk1;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}
