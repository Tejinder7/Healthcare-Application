package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Service.FieldWorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FieldWorkerController {
    private FieldWorkerService fieldWorkerService;
    public FieldWorkerController(FieldWorkerService fieldWorkerService) {
        this.fieldWorkerService = fieldWorkerService;
    }

    @PostMapping("/addFieldWorker/{supervisorId}")
    public ResponseEntity<FieldWorker> addFieldWorker(@RequestBody FieldWorker fieldWorker, @PathVariable int supervisorId) {

        try {
            fieldWorker = fieldWorkerService.addFieldWorker(fieldWorker, supervisorId);
        }catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkers/{supervisorId}")
    public ResponseEntity<List<FieldWorker>> getFieldWorkers(@PathVariable int supervisorId){
        List<FieldWorker> fieldWorkerList;

        try{
            fieldWorkerList = fieldWorkerService.getFieldWorkers(supervisorId);
        }
        catch (Exception exception){
            throw new ResourceNotFoundException("No Field Workers under the SupervisorId: "+ supervisorId);
        }
        return ResponseEntity.ok(fieldWorkerList);
    }


    @PutMapping ("/assignFollowUp/{fieldWorkerId}/{patientId}")
    public ResponseEntity<FieldWorker> assignFollowUp(@PathVariable int fieldWorkerId, @PathVariable int patientId){
        FieldWorker fieldWorker;

        try {
            fieldWorker = fieldWorkerService.assignFollowUp(patientId, fieldWorkerId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(fieldWorker));
    }

    @GetMapping("/getFieldWorkerDetail/{fieldWorkerId}")
    public ResponseEntity<FieldWorker> getFieldWorkerDetail(@PathVariable int fieldWorkerId){
        FieldWorker fieldWorker;
        try{
            fieldWorker = fieldWorkerService.getFieldWorkerById(fieldWorkerId);
            return ResponseEntity.of(Optional.of(fieldWorker));
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }

}
