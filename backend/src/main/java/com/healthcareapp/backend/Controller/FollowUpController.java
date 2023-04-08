package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Service.FollowUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FollowUpController {
    private FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @GetMapping("/getTodayFollowUps/{date}/{fieldWorkerId}")
    public ResponseEntity<List<FollowUp>> getTodayFollowUp(@PathVariable String date, @PathVariable int fieldWorkerId){
        List<FollowUp> followUpList;

        try {
            followUpList = followUpService.getCurrentDateFollowUps(date, fieldWorkerId);
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(followUpList);
    }


    @PutMapping("/updateFollowUpByFieldWorker")
    public ResponseEntity<FollowUp> updateFollowUpByFieldWorker(@RequestBody FollowUp followUp){
        try{
            followUpService.updateFollowUpByFieldWorker(followUp);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUp));
    }

    @GetMapping("/getFollowUps/{userId}")
    public ResponseEntity<List<FollowUp>> getFollowUps(@PathVariable String userId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpService.getAllFollowUp(userId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(followUpList));
    }

    @PostMapping("/addFollowUps/{en_id}")
    public ResponseEntity<List<FollowUp>> addFollowUps(@RequestBody List<String> dateList, @PathVariable int en_id){
        List<FollowUp> followUpList = followUpService.addFollowUps(dateList, en_id);
        return ResponseEntity.of(Optional.of(followUpList));
    }

    @GetMapping("/getFollowUpsForFieldWorker/{fieldWorkerId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsForFieldWorker(@PathVariable int fieldWorkerId){
        List<FollowUp> followUpList = followUpService.getFollowUpsByFieldWorker(fieldWorkerId);
        return ResponseEntity.of(Optional.of(followUpList));
    }

    @GetMapping("/getListOfFollowUpsAssignedBy/{doctorUserId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsAssignedByDoctor(@PathVariable String doctorUserId){
        List<FollowUp> followUpList;
        try{
            followUpList= followUpService.getAllFollowUpsAssignedByDoctor(doctorUserId);
        }
        catch(RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUpList));
    }
}
