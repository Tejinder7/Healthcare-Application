package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Service.EncounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EncounterController {
    private EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
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

    @PostMapping("addEncounters/{pid}/{docId}")
    public ResponseEntity<Encounter> addEncounter(@PathVariable int pid, @PathVariable int docId){
        Encounter savedEncounter;

        try{
            savedEncounter = encounterService.addEncounter(pid, docId);
        }
        catch (RuntimeException exception) {
            throw exception;
        }

        return ResponseEntity.of(Optional.of(savedEncounter));
    }

    @PutMapping("saveEncounter/{eid}")
    public ResponseEntity<Encounter> completeEncounter(@RequestBody Encounter encounter){
        Encounter updatedEncounter;
        try {
            updatedEncounter = encounterService.updateEncounter(encounter);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedEncounter));
    }

}
