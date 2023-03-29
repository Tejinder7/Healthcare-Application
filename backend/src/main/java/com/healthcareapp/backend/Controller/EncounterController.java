package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Service.EncounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class EncounterController {
    private EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping("addEncounters/{pid}/{docId}")
    public ResponseEntity<Encounter> addEncounter(@RequestBody Encounter encounter, @PathVariable int pid, @PathVariable int docId){
        Encounter encounter1;
        try{
            encounter1 = encounterService.addEncounter(pid, docId);
        }
        catch(Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(encounter1));
    }

    @PostMapping("saveEncounter/{eid}")
    public ResponseEntity<MedicalHistory> saveEncounter(@RequestBody MedicalHistory medicalHistory, @PathVariable int eid){
        MedicalHistory medicalHistory1;
        try {
            medicalHistory1 = encounterService.saveEncounter(medicalHistory.getPrescription(), medicalHistory.getSymptoms(), eid);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(medicalHistory1));
    }

}
