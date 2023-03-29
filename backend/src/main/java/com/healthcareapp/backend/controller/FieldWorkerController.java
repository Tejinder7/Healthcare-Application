package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.FieldWorker;
import com.healthcareapp.backend.entities.FrontDesk;
import com.healthcareapp.backend.services.FieldWorkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FieldWorkerController {

    @Autowired
    private FieldWorkerServices fieldWorkerService;

    @PostMapping("/addFieldWorker")
    public ResponseEntity<FieldWorker> addFieldWorker(@RequestParam("name") String name, @RequestParam("address") String address,  @RequestParam("phoneNum") String phoneNum,@RequestParam("supId") int supId) {
        FieldWorker fieldWorker;

        try {
            fieldWorker = fieldWorkerService.addFieldWorker(name, address, phoneNum, supId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkers")
    public ResponseEntity<List<FieldWorker>> getFieldWorkers(@RequestParam("supId") int supId){
        List<FieldWorker> fieldWorkerList;

        try{
            fieldWorkerList = fieldWorkerService.getFieldWorkers(supId);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(fieldWorkerList);
    }


    @PutMapping ("/assignFollowUp")
    public ResponseEntity<FieldWorker> assignFollowUp(@RequestParam("fieldWorkerId") int fieldWorkerId, @RequestParam("followUpId") int followUpId){
        FieldWorker fieldWorker;

        try {
            fieldWorker = fieldWorkerService.assignFollowUp(followUpId, fieldWorkerId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(fieldWorker));
    }

}
