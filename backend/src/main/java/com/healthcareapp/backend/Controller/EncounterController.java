package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class EncounterController {

    @Autowired
    private EncounterService encounterService;

    @PostMapping("addEncounters/{pid}/{docId}")
    public ResponseEntity<Encounter> addEncounter(@RequestBody Encounter encounter, @PathVariable int pid, @PathVariable int docId){
        Encounter en;
        try{
            en = encounterService.addEncounter(pid, docId);
        }
        catch(Exception e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.of(Optional.of(en));
    }

    @PostMapping("saveEncounter/{eid}")
    public ResponseEntity<MedicalHistory> saveEncounter(@RequestBody MedicalHistory medicalHistory, @PathVariable int eid){
        MedicalHistory mh;
        try {
            mh = encounterService.saveEncounter(medicalHistory.getPrescription(), medicalHistory.getSymptoms(), eid);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(mh));
    }

}
