package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Hospital;
import com.healthcareapp.backend.entities.Patient;
import com.healthcareapp.backend.entities.PendingQueue;
import com.healthcareapp.backend.patientservice.HospitalServices;
import com.healthcareapp.backend.patientservice.PatientServices;
import com.healthcareapp.backend.patientservice.PendingQueueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PendingQueueController {

    @Autowired
    private PendingQueueServices pendingQueueServices;

    @Autowired
    private HospitalServices hospitalServices;

    @Autowired
    private PatientServices patientServices;

    @PostMapping("/addPendingQueue/{hosp_id}/{p_id}")
    public PendingQueue addPendingQueue(@RequestBody PendingQueue pq, @PathVariable("hosp_id") int hosp_id, @PathVariable("p_id") int p_id){

        Hospital hospital = this.hospitalServices.getHospitalById(hosp_id);
        Patient patient = this.patientServices.getPatientById(p_id);

        pq.setHospital(hospital);
        pq.setPatient(patient);

        PendingQueue p = this.pendingQueueServices.addPendingQueue(pq);
        return p;
    }

}
