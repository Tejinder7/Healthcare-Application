package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Service.PendingQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PendingQueueController {

    @Autowired
    private PendingQueueService pendingQueueService;

    @PostMapping("/addPendingQueue/{hosp_id}/{p_id}")
    public ResponseEntity<PendingQueue> addPendingQueue(@PathVariable("hosp_id") int hosp_id, @PathVariable("p_id") int p_id){

        PendingQueue pq;
        try {
            pq = this.pendingQueueService.addPendingQueue(hosp_id, p_id);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(pq));
    }

    @GetMapping("/pendingQueue/{docId}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable("docId") int docId){

        try{
            List<PendingQueue> list = pendingQueueService.getPendingQueueByDocId(docId);
            return ResponseEntity.of(Optional.of(list));
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

}
