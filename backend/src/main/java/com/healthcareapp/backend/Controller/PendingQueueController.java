package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Service.PendingQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PendingQueueController {
    private PendingQueueService pendingQueueService;

    public PendingQueueController(PendingQueueService pendingQueueService) {
        this.pendingQueueService = pendingQueueService;
    }

    @PostMapping("/addPendingQueue/{hosp_id}/{p_id}")
    public ResponseEntity<PendingQueue> addPendingQueue(@PathVariable("hosp_id") int hosp_id, @PathVariable("p_id") int p_id){

        PendingQueue pendingQueue;
        try {
            pendingQueue = this.pendingQueueService.addPendingQueue(hosp_id, p_id);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(pendingQueue));
    }

    @GetMapping("/pendingQueue/{docId}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable("docId") int docId){

        try{
            List<PendingQueue> pendingQueueList = pendingQueueService.getPendingQueueByDocId(docId);
            return ResponseEntity.of(Optional.of(pendingQueueList));
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
