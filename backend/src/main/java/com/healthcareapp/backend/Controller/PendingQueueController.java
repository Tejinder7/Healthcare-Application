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
    public ResponseEntity<PendingQueue> addPendingQueue(@PathVariable int hosp_id, @PathVariable int p_id){

        PendingQueue pendingQueue;
        try {
            pendingQueue = this.pendingQueueService.addPendingQueue(hosp_id, p_id);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(pendingQueue));
    }

    @GetMapping("/pendingQueue/{hospId}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable int hospId){

        try{
            List<PendingQueue> pendingQueueList = pendingQueueService.getPendingQueueByDocId(hospId);
            return ResponseEntity.of(Optional.of(pendingQueueList));
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
