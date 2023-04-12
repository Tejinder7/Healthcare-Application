package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Service.EncounterService;
import com.healthcareapp.backend.Service.FollowUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EncounterController {
    private EncounterService encounterService;
    private FollowUpService followUpService;

    public EncounterController(EncounterService encounterService, FollowUpService followUpService) {
        this.encounterService = encounterService;
        this.followUpService = followUpService;
    }

    @GetMapping("getMedicalHistory/{patientId}")
    public ResponseEntity<List<Encounter>> getMedicalHistoryFromEncounters(@PathVariable int patientId){
        List<Encounter> encounterList;

        try{
            encounterList= encounterService.getEncountersByPatientId(patientId);
        }
        catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(encounterList));
    }

    @PostMapping("addEncounter/{patientid}/{doctorUserId}")
    public ResponseEntity<Encounter> addEncounter(@PathVariable int patientid, @PathVariable String doctorUserId){
        Encounter savedEncounter;

        try{
            savedEncounter = encounterService.addEncounter(patientid, doctorUserId);
        }
        catch (RuntimeException exception) {
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedEncounter));
    }

    @GetMapping("/getUnsavedEncounters/{doctorUserId}")
    public ResponseEntity<List<Encounter>> getUnsavedEncounters(@PathVariable String doctorUserId){
        List<Encounter> encounterList;
        try{
            encounterList = encounterService.getUnsavedEncounters(doctorUserId);
            return ResponseEntity.of(Optional.of(encounterList));
        }catch (Exception exception){
            throw exception;
        }
    }

    @PutMapping("/saveEncounter")
    public ResponseEntity<Encounter> completeEncounter(@RequestBody Encounter encounter){
        Encounter updatedEncounter;
        try {
            updatedEncounter = encounterService.updateEncounter(encounter);
        }catch (RuntimeException exception){
            throw exception;
        }

        try{
            List<FollowUp> followUpList = followUpService.addFollowUps(encounter);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedEncounter));
    }

}
