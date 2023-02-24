package com.healthcareapp.backend.patientservice;

import com.healthcareapp.backend.dao.DoctorDao;
import com.healthcareapp.backend.entities.Doctor;
import com.healthcareapp.backend.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorService {

    @Autowired
    private DoctorDao dDao;

    public Hospital findHospitalfromDoctor(int id){
        Doctor d = dDao.findById(id);

        Hospital h = d.getHospital();

        if(h==null)
            throw new RuntimeException();
        else
            return h;
    }
}
