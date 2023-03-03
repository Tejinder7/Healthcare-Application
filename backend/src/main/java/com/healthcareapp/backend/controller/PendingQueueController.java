package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.PendingQueue;
import com.healthcareapp.backend.patientservice.PendingQueueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class PendingQueueController {

    @Autowired

    private PendingQueueServices pendingQueueServices;

    @PostMapping("/addPendingQueue/{hosp_id}/{p_id}")
    public ResponseEntity<PendingQueue> addPendingQueue(@RequestBody PendingQueue pq, @PathVariable("hosp_id") int hosp_id, @PathVariable("p_id") int p_id){

        try {
            pq = pendingQueueServices.setPendingQueueDateTime(pq);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
        try {
            pq = pendingQueueServices.setPendingQueuePatient(pq, p_id);
            pq = pendingQueueServices.setPendingQueueHospital(pq, hosp_id);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        pendingQueueServices.addPendingQueue(pq);
        return ResponseEntity.of(Optional.of(pq));
    }

    @GetMapping("/pendingQueue/{id}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable("id") int id){

        try{
            List<PendingQueue> list = pendingQueueServices.getPendingQueuebyDid(id);
            return ResponseEntity.of(Optional.of(list));
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

    }

}
