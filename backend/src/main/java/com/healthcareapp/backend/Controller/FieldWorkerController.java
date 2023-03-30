package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Service.FieldWorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FieldWorkerController {
    private FieldWorkerService fieldWorkerService;
    public FieldWorkerController(FieldWorkerService fieldWorkerService) {
        this.fieldWorkerService = fieldWorkerService;
    }


    @PostMapping("/addFieldWorker/{supervisorId}")
    public ResponseEntity<FieldWorker> addFieldWorker(@RequestBody FieldWorker fieldWorker, @PathVariable int supervisorAuthId) {

        try {
            fieldWorker = fieldWorkerService.addFieldWorker(fieldWorker, supervisorAuthId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkers/{supervisorId}")
    public ResponseEntity<List<FieldWorker>> getFieldWorkers(@PathVariable int supervisorAuthId){
        List<FieldWorker> fieldWorkerList;

        try{
            fieldWorkerList = fieldWorkerService.getFieldWorkers(supervisorAuthId);
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(fieldWorkerList);
    }


    @PutMapping ("/assignFollowUp/{fieldWorkerId}/{followUpId}")
    public ResponseEntity<FieldWorker> assignFollowUp(@PathVariable int fieldWorkerId, @PathVariable int followUpId){
        FieldWorker fieldWorker;

        try {
            fieldWorker = fieldWorkerService.assignFollowUp(followUpId, fieldWorkerId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(fieldWorker));
    }

}
