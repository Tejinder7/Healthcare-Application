package com.healthcareapp.backend.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Service.EncounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EncounterController {
    private EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping("addEncounters/{pid}/{docId}")
    public ResponseEntity<Encounter> addEncounter(@PathVariable int pid, @PathVariable int docId){
        Encounter encounter1;
        try{
            encounter1 = encounterService.addEncounter(pid, docId);
        }
        catch(Exception exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(encounter1));
    }

    @PostMapping("saveEncounter/{eid}")
    public MappingJacksonValue saveEncounter(@RequestBody MedicalHistory medicalHistory, @PathVariable int eid){
        MedicalHistory medicalHistory1;
        try {
            medicalHistory1 = encounterService.saveEncounter(medicalHistory.getPrescription(), medicalHistory.getSymptoms(), eid);
//            return ResponseEntity.of(Optional.of(true));
            MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(medicalHistory1);
            SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("medicalHistoryId", "patient", "symptoms", "prescription");
            FilterProvider filters= new SimpleFilterProvider().addFilter("MedicalHistoryFilter", filter);

            mappingJacksonValue.setFilters(filters);

            return mappingJacksonValue;
        }catch (Exception exception){
            throw exception;
        }
    }

}
