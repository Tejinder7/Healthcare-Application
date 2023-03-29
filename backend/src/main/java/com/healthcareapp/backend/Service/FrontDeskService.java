package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.FrontDeskRepository;
import com.healthcareapp.backend.Repository.HospitalRepository;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrontDeskService {

    @Autowired
    private FrontDeskRepository frontDeskRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public FrontDesk addFrontDesk(String name, int hospId, String userId, String password){
        FrontDesk frontDesk = new FrontDesk();

        Hospital hosp;

        hosp = hospitalRepository.getHospitalsByHospId(hospId);

        if(hosp==null)
        {
            throw new RuntimeException();
        }

        frontDesk.setName(name);
        frontDesk.setHospId(hosp);
        frontDesk.setUserId(userId);
        frontDesk.setPassword(password);
        frontDesk.setUserType("FrontDesk");

        try {
            frontDeskRepository.save(frontDesk);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return frontDesk;
    }
}
