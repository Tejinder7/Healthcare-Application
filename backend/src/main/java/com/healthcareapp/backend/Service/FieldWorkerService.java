package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Repository.FieldWorkerRepository;
import com.healthcareapp.backend.Repository.SupervisorRepository;
import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Model.Supervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldWorkerService {

    @Autowired
    private FieldWorkerRepository fieldWorkerRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    public FieldWorker addFieldWorker(String name, String address, String phoneNum, int supId){
        FieldWorker fieldWorker = new FieldWorker();

        Supervisor supervisor;

        supervisor = supervisorRepository.findSupervisorBySupId(supId);

        if(supervisor==null)
        {
            throw new RuntimeException();
        }

        fieldWorker.setName(name);
        fieldWorker.setAddress(address);
        fieldWorker.setPhoneNo(phoneNum);
        fieldWorker.setSupId(supervisor);

        try {
            fieldWorker = fieldWorkerRepository.save(fieldWorker);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return fieldWorker;
    }



    public List<FieldWorker> getFieldWorkers(int supId){
        Supervisor sup = supervisorRepository.findSupervisorBySupId(supId);

        if(sup==null)
        {
            throw new RuntimeException();
        }

        List<FieldWorker> fieldWorkerList = fieldWorkerRepository.findBySupId(sup);

        if(fieldWorkerList.size()==0)
        {
            throw new RuntimeException();
        }
        else
            return fieldWorkerList;
    }
}
