package com.healthcareapp.backend.controller;

import com.healthcareapp.backend.entities.Encounter;
import com.healthcareapp.backend.entities.MedicalHistory;
import com.healthcareapp.backend.services.EncounterServices;
import com.healthcareapp.backend.services.MedicalHistoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class EncounterController {

    @Autowired
    private EncounterServices encounterServices;

    @PostMapping("addEncounters/{pid}/{docId}")
    public ResponseEntity<Encounter> addEncounter(@RequestBody Encounter encounter, @PathVariable int pid, @PathVariable int docId){
        Encounter en;
        try{
            en = encounterServices.addEncounter(pid, docId);
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
            mh = encounterServices.saveEncounter(medicalHistory.getPrescription(), medicalHistory.getSymptoms(), eid);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(mh));
    }

}
