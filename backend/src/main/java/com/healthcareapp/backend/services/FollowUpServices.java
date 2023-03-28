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

    @Autowired
    private EncounterServices encounterServices;

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

        try {
            followUp = followUpDao.save(followUp);
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

    public List<FollowUp> addFollowUps(List<String> dateList, int en_id){
        Encounter encounter = encounterServices.getEncounterById(en_id);
        Patient patient = encounter.getPatientId();
        Doctor doctor = encounter.getDoctorId();
        Hospital hospital = doctor.getHospId();
        List<FollowUp> fuList = new ArrayList<>();
        try {
            for (int i = 0; i < dateList.size(); i++) {
                FollowUp fu = new FollowUp();
                fu.setEncounterId(encounter);
                fu.setDate(dateList.get(i));
                fu.setFlag(false);
                fu.setPatientId(patient);
                fu.setHospId(hospital);
                followUpDao.save(fu);

                fuList.add(fu);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fuList;
    }
}
