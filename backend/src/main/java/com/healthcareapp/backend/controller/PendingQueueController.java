package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.PendingQueue;
import com.healthcareapp.backend.patientservice.PendingQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PendingQueueController {

    @Autowired
    private PendingQueueService pendingQueueService;

    @GetMapping("/pendingQueue/{id}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable("id") int id){
        List<PendingQueue> list = pendingQueueService.getPendingQueuebyDid(id);

        if(list.size()==0)
            return ResponseEntity.status(404).build();
        return ResponseEntity.of(Optional.of(list));
    }
}
