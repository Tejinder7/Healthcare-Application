package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Service.PendingQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PendingQueueController {
    private PendingQueueService pendingQueueService;

    public PendingQueueController(PendingQueueService pendingQueueService) {
        this.pendingQueueService = pendingQueueService;
    }

    @PostMapping("/addPendingQueue/{userId}/{pid}")
    public ResponseEntity<PendingQueue> addPendingQueue(@PathVariable String userId, @PathVariable int pid){

        PendingQueue pendingQueue;
        try {
            pendingQueue = this.pendingQueueService.addPendingQueue(userId, pid);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(pendingQueue));
    }

    @GetMapping("/pendingQueue/{doctorUserId}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable String doctorUserId){

        try{
            List<PendingQueue> pendingQueueList = pendingQueueService.getPendingQueueByDocId(doctorUserId);
            return ResponseEntity.of(Optional.of(pendingQueueList));
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
