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


    public FieldWorker addFieldWorker(FieldWorker fieldWorker, int supId){

        Supervisor supervisor;

        supervisor = supervisorRepository.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        fieldWorker.setSupId(supervisor);
        fieldWorker.setUserType("Field Worker");

        try {
            fieldWorker = fieldWorkerRepository.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fieldWorker;
    }



    public List<FieldWorker> getFieldWorkers(int supId){
        Supervisor supervisor = supervisorRepository.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findBySupId(supervisor);

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }

    public FieldWorker assignFollowUp(int followUpId, int fieldWorkerId){

        FollowUp followUp = followUpRepository.findById(followUpId);

        FieldWorker fieldWorker = fieldWorkerRepository.findByFwId(fieldWorkerId);

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

        fieldWorker = fieldWorkerRepository.findByFwId(fieldWorkerId);

        return fieldWorker;
    }
}
