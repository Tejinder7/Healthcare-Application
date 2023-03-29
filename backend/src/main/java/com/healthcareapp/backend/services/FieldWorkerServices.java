package com.healthcareapp.backend.services;

import com.healthcareapp.backend.dao.FieldWorkerDao;
import com.healthcareapp.backend.dao.FollowUpDao;
import com.healthcareapp.backend.dao.PatientDao;
import com.healthcareapp.backend.dao.SupervisorDao;
import com.healthcareapp.backend.entities.FieldWorker;
import com.healthcareapp.backend.entities.FollowUp;
import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.entities.Supervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldWorkerServices {

    @Autowired
    private FieldWorkerDao fieldWorkerDao;

    @Autowired
    private SupervisorDao supervisorDao;

    @Autowired
    private FollowUpDao followUpDao;

    @Autowired
    private PatientDao patientDao;

    public FieldWorker addFieldWorker(String name, String address, String phoneNum, int supId){
        FieldWorker fieldWorker = new FieldWorker();

        Supervisor supervisor;

        supervisor = supervisorDao.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        fieldWorker.setName(name);
        fieldWorker.setAddress(address);
        fieldWorker.setPhoneNo(phoneNum);
        fieldWorker.setSupId(supervisor);
        fieldWorker.setPatientList(new ArrayList<>());

        try {
            fieldWorker = fieldWorkerDao.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fieldWorker;
    }



    public List<FieldWorker> getFieldWorkers(int supId){
        Supervisor sup = supervisorDao.findSupervisorBySupId(supId);

        if(sup==null)
        {
            throw new RuntimeException();
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerDao.findBySupId(sup);

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }

    public FieldWorker assignFollowUp(int followUpId, int fieldWorkerId){

        FollowUp followUp = followUpDao.findById(followUpId);

        FieldWorker fieldWorker = fieldWorkerDao.findByFwId(fieldWorkerId);

        if(followUp==null || fieldWorker==null)
        {
            throw new RuntimeException();
        }

        Patient patient = followUp.getPatientId();

//        List<Patient> list = fieldWorker.getPatientList();
//
//        list.add(patient);
//
//        fieldWorker.setPatientList(list);

        patient = patientDao.findPatientByPatientId(patient.getPatientId());

        patient.setFieldWorkerId(fieldWorker);

        try{
            patient = patientDao.save(patient);
            //fieldWorker = fieldWorkerDao.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }

        fieldWorker = fieldWorkerDao.findByFwId(fieldWorkerId);

        return fieldWorker;
    }
}
