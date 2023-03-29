package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.FieldWorkerDao;
import com.healthcareapp.backend.dao.FollowUpDao;
import com.healthcareapp.backend.dao.SupervisorDao;
import com.healthcareapp.backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FollowUpServices {

    @Autowired
    private FollowUpDao followUpDao;

    @Autowired
    private FieldWorkerDao fieldWorkerDao;

    @Autowired
    private SupervisorDao supervisorDao;

    public List<FollowUp> getCurrentDateFollowUps(String date, int fwId){
        FieldWorker fieldWorker;

        fieldWorker = fieldWorkerDao.findByFwId(fwId);

        if(fieldWorker==null)
        {
            throw new RuntimeException();
        }

        List<Patient> patientList = fieldWorker.getPatientList();

        List<FollowUp> followUpList = new ArrayList<>();

        patientList.forEach(patient -> {followUpList.addAll(followUpDao.findByDateAndPatientId(date, patient));});

        return followUpList;
    }


    public FollowUp updateFollowUp(FollowUp followUp){

        int followUpId = followUp.getFollowUpId();

        FollowUp validFollowUp = followUpDao.findById(followUpId);

        if(validFollowUp==null)
        {
            throw new RuntimeException();
        }

        validFollowUp.setFlag(followUp.isFlag());
        validFollowUp.setLastSyncDate(followUp.getLastSyncDate());
        validFollowUp.setRemarks(followUp.getRemarks());

        try {
            followUp = followUpDao.save(validFollowUp);
        }catch (Exception e){
            throw new RuntimeException();
        }

        return followUp;
    }

    public List<FollowUp> getAllFollowUp(int supId){
        Supervisor sup = supervisorDao.findSupervisorBySupId(supId);

        if(sup==null)
        {
            throw new RuntimeException();
        }

        List<FollowUp> followUpList = new ArrayList<FollowUp>();

        List<Hospital> hospitalList = sup.getHospitalList();

        hospitalList.forEach(hospital -> {followUpList.add(followUpDao.findByHospId(hospital));});

        return followUpList;
    }
}
