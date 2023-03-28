package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.FrontDeskDao;
import com.healthcareapp.backend.dao.HospitalDao;
import com.healthcareapp.backend.entities.FrontDesk;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrontDeskServices {

    @Autowired
    private FrontDeskDao frontDeskDao;

    @Autowired
    private HospitalDao hospitalDao;

    public FrontDesk addFrontDesk(String name, int hospId){
        FrontDesk frontDesk = new FrontDesk();

        Hospital hosp;

        hosp = hospitalDao.getHospitalsByHospId(hospId);

        if(hosp==null)
        {
            throw new RuntimeException();
        }

        frontDesk.setName(name);
        frontDesk.setHospId(hosp);

        try {
            frontDeskDao.save(frontDesk);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return frontDesk;
    }
}
