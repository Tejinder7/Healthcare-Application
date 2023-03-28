package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.FollowUp;
import com.healthcareapp.backend.services.FollowUpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FollowUpController {

    @Autowired
    private FollowUpServices followUpServices;

    @GetMapping("/getTodaysFollowUps")
    public ResponseEntity<List<FollowUp>> getTodayFollowUp(@RequestParam("date") String date, @RequestParam("fwId") int fwId){
        List<FollowUp> followUpList;

        try {
            followUpList = followUpServices.getCurrentDateFollowUps(date, fwId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok(followUpList);
    }


    @PutMapping("/updateFollowUp")
    public ResponseEntity<FollowUp> updateFollowUp(@RequestBody FollowUp followUp){
        try{
            followUpServices.updateFollowUp(followUp);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(followUp));
    }

    @GetMapping("/getFollowUps")
    public ResponseEntity<List<FollowUp>> getFollowUps(@RequestParam("supId") int supId){
        List<FollowUp> followUpList;
        try{
            followUpList = followUpServices.getAllFollowUp(supId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(followUpList));
    }

    @PostMapping("/addFollowUps/{en_id}")
    public ResponseEntity<List<FollowUp>> addFollowUps(@RequestBody List<String> dateList, @PathVariable int en_id){
        List<FollowUp> fuList = followUpServices.addFollowUps(dateList, en_id);
        return ResponseEntity.of(Optional.of(fuList));
    }
}
