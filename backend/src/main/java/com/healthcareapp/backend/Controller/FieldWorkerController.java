package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.FieldWorker;
import com.healthcareapp.backend.Service.FieldWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FieldWorkerController {

    @Autowired
    private FieldWorkerService fieldWorkerService;

    @PostMapping("/addFieldWorker/{supId}")
    public ResponseEntity<FieldWorker> addFieldWorker(@RequestBody FieldWorker fieldWorker, @PathVariable("supId") int supId) {
        FieldWorker fieldWorker1;

        try {
            fieldWorker1 = fieldWorkerService.addFieldWorker(fieldWorker.getName(), fieldWorker.getAddress(), fieldWorker.getPhoneNo(), supId);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(fieldWorker1));
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
