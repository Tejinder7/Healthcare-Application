package com.healthcareapp.backend.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.MedicalHistory;
import com.healthcareapp.backend.Service.MedicalHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalHistoryController {
    private MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping("getMedicalHistory/{pid}")
    public MappingJacksonValue getMedicalHistory(@PathVariable int pid){
        List<MedicalHistory> medicalHistoryList;
        try {
            medicalHistoryList = medicalHistoryService.getMedicalHistoryByPatientId(pid);
        }
        catch (RuntimeException exception){
            throw exception;
        }
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(medicalHistoryList);
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("medicalHistoryId", "symptoms", "prescription");
        FilterProvider filters= new SimpleFilterProvider().addFilter("MedicalHistoryFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}
