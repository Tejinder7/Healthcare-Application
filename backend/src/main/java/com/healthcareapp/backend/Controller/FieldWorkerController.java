package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FieldWorker;
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

    @GetMapping("/getFollowUpsForFieldWorkerMobile/{fieldWorkerUsername}/{followUpId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsForFieldWorker(@PathVariable String fieldWorkerUsername, @PathVariable int followUpId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpService.getFollowUpsByFieldWorkerMobile(fieldWorkerUsername, followUpId);
        }catch (RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUpList));
    }

//    @GetMapping("/getTodayFollowUps/{date}/{fieldWorkerId}")
//    public ResponseEntity<List<FollowUp>> getTodayFollowUp(@PathVariable String date, @PathVariable int fieldWorkerId){
//        List<FollowUp> followUpList;
//        try {
//            followUpList = followUpService.getCurrentDateFollowUps(date, fieldWorkerId);
//        }catch (Exception exception){
//            throw exception;
//        }
//        return ResponseEntity.ok(followUpList);
//    }

    @PutMapping("/updateFollowUpsByFieldWorker")
    public ResponseEntity<List<Integer>> updateFollowUpsByFieldWorker(@RequestBody List<FollowUp> followUpList){
        try{
            List<Integer> followUpListUpdated = followUpService.updateFollowUp(followUpList);
            return ResponseEntity.of(Optional.of(followUpListUpdated));
        }
        catch (Exception exception){
            throw exception;
        }
    }

    @GetMapping("/getFieldWorker/{fieldWorkerUsername}")
    public ResponseEntity<FieldWorker> getFieldWorkerByUsername(@PathVariable String fieldWorkerUsername){
        FieldWorker fieldworker = new FieldWorker();
        try{
            fieldworker = fieldWorkerService.getFieldWorkerByUsername(fieldWorkerUsername);
            return ResponseEntity.of(Optional.of(fieldworker));
        }catch (RuntimeException exception){
            throw exception;
        }
    }
}
