package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
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
import java.util.Optional;

@Component
public class FieldWorkerService {
    private FieldWorkerRepository fieldWorkerRepository;
    private SupervisorRepository supervisorRepository;
    private FollowUpRepository followUpRepository;
    private PatientRepository patientRepository;

    private PatientService patientService;
    
    public FieldWorkerService(FieldWorkerRepository fieldWorkerRepository, SupervisorRepository supervisorRepository, FollowUpRepository followUpRepository, PatientRepository patientRepository, PatientService patientService) {
        this.fieldWorkerRepository = fieldWorkerRepository;
        this.supervisorRepository = supervisorRepository;
        this.followUpRepository = followUpRepository;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }


    public FieldWorker addFieldWorker(FieldWorker fieldWorker, String userId){

        Optional<Supervisor> supervisor = supervisorRepository.findSupervisorByUserId(userId);

        if(supervisor.isEmpty())
        {
            throw new RuntimeException();
        }

        fieldWorker.setSupervisor(supervisor.get());
        fieldWorker.setUserType("FieldWorker");
        fieldWorker.setAvailableStatus(true);

        try {
            fieldWorker = fieldWorkerRepository.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fieldWorker;
    }



    public List<FieldWorker> getFieldWorkers(String userId){
        Optional<Supervisor> supervisor = supervisorRepository.findSupervisorByUserId(userId);

        if(supervisor.isEmpty())
        {
            throw new ResourceNotFoundException("Supervisor with userId" + userId + "not found");
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findFieldWorkerBySupervisor(supervisor.get());

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }

    public List<FieldWorker> getAvailableFieldWorkers(String userId){
        Optional<Supervisor> supervisor = supervisorRepository.findSupervisorByUserId(userId);

        if(supervisor.isEmpty())
        {
            throw new ResourceNotFoundException("Supervisor with userId" + userId + "not found");
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findFieldWorkerBySupervisorAndAvailableStatusIsTrue(supervisor.get());

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }

    public FieldWorker assignFollowUp(int patientId, int fieldWorkerAuthId){

        Patient patient = patientService.getPatientById(patientId);

//        List<FollowUp> followUpList = followUpRepository.findByPatient(patient);

        FieldWorker fieldWorker = fieldWorkerRepository.findFieldWorkerByAuthId(fieldWorkerAuthId);

        if(patient==null || fieldWorker==null)
        {
            throw new RuntimeException();
        }

//        Patient patient = followUp.getPatient();

//        List<Patient> list = fieldWorker.getPatientList();
//
//        list.add(patient);
//
//        fieldWorker.setPatientList(list);

//        patient = patientRepository.findPatientByPatientId(patient.getPatientId());

        patient.setFieldWorker(fieldWorker);

        try{
            patientService.updatePatient(patient);
            //fieldWorker = fieldWorkerDao.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }

        fieldWorker = fieldWorkerRepository.findFieldWorkerByAuthId(fieldWorkerAuthId);

        return fieldWorker;
    }

    public FieldWorker getFieldWorkerById(int fieldWorkerId){
        FieldWorker fieldWorker = fieldWorkerRepository.findFieldWorkerByAuthId(fieldWorkerId);
        return fieldWorker;
    }
}
