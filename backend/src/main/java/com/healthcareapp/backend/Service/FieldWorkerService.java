package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.Patient;
import com.healthcareapp.backend.Model.Supervisor;
import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.FollowUpRepository;
import com.healthcareapp.backend.Repository.PatientRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldWorkerService {
    private FieldWorkerRepository fieldWorkerRepository;
    private SupervisorRepository supervisorRepository;
    private FollowUpRepository followUpRepository;
    private PatientRepository patientRepository;
    
    public FieldWorkerService(FieldWorkerRepository fieldWorkerRepository, SupervisorRepository supervisorRepository, FollowUpRepository followUpRepository, PatientRepository patientRepository) {
        this.fieldWorkerRepository = fieldWorkerRepository;
        this.supervisorRepository = supervisorRepository;
        this.followUpRepository = followUpRepository;
        this.patientRepository = patientRepository;
    }


    public FieldWorker addFieldWorker(FieldWorker fieldWorker, int authId){

        Supervisor supervisor;

        supervisor = supervisorRepository.findSupervisorByAuthId(authId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        fieldWorker.setSupAuthId(supervisor);
        fieldWorker.setUserType("FieldWorker");

        try {
            fieldWorker = fieldWorkerRepository.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fieldWorker;
    }



    public List<FieldWorker> getFieldWorkers(int authId){
        Supervisor supervisor = supervisorRepository.findSupervisorByAuthId(authId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findBySupAuthId(supervisor);

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }

    public FieldWorker assignFollowUp(int followUpId, int fieldWorkerAuthId){

        FollowUp followUp = followUpRepository.findById(followUpId);

        FieldWorker fieldWorker = fieldWorkerRepository.findFieldWOrkerByAuthId(fieldWorkerAuthId);

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

        patient = patientRepository.findPatientByPatientId(patient.getPatientId());

        patient.setFieldWorkerId(fieldWorker);

        try{
            patient = patientRepository.save(patient);
            //fieldWorker = fieldWorkerDao.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }

        fieldWorker = fieldWorkerRepository.findFieldWOrkerByAuthId(fieldWorkerAuthId);

        return fieldWorker;
    }
}
