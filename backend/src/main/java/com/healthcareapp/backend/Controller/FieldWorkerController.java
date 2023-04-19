package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Service.FieldWorkerService;
import com.healthcareapp.backend.Service.FollowUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fieldworker")
public class FieldWorkerController {
    private FieldWorkerService fieldWorkerService;
    private FollowUpService followUpService;

    public FieldWorkerController(FieldWorkerService fieldWorkerService, FollowUpService followUpService) {
        this.fieldWorkerService = fieldWorkerService;
        this.followUpService = followUpService;
    }

    @GetMapping("/getFollowUpsForFieldWorker/{fieldWorkerId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsForFieldWorker(@PathVariable int fieldWorkerId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpService.getFollowUpsByFieldWorker(fieldWorkerId);
        }catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUpList));
    }
    @GetMapping("/getTodayFollowUps/{date}/{fieldWorkerId}")
    public ResponseEntity<List<FollowUp>> getTodayFollowUp(@PathVariable String date, @PathVariable int fieldWorkerId){
        List<FollowUp> followUpList;

        try {
            followUpList = followUpService.getCurrentDateFollowUps(date, fieldWorkerId);
        }catch (Exception exception){
            throw exception;
        }

        return ResponseEntity.ok(followUpList);
    }
    @PutMapping("/updateFollowUpByFieldWorker")
    public ResponseEntity<FollowUp> updateFollowUpByFieldWorker(@RequestBody FollowUp followUp){
        try{
            followUpService.updateFollowUp(followUp);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUp));
    }
}
