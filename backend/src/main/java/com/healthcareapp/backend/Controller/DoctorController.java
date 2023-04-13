package com.healthcareapp.backend.Controller;

import com.healthcareapp.backend.Model.Encounter;
import com.healthcareapp.backend.Model.FollowUp;
import com.healthcareapp.backend.Model.PendingQueue;
import com.healthcareapp.backend.Service.DoctorService;
import com.healthcareapp.backend.Service.EncounterService;
import com.healthcareapp.backend.Service.FollowUpService;
import com.healthcareapp.backend.Service.PendingQueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;

    private PendingQueueService pendingQueueService;

    private EncounterService encounterService;

    private FollowUpService followUpService;

    public DoctorController(DoctorService doctorService, PendingQueueService pendingQueueService, EncounterService encounterService, FollowUpService followUpService) {
        this.doctorService = doctorService;
        this.pendingQueueService = pendingQueueService;
        this.encounterService = encounterService;
        this.followUpService = followUpService;
    }

    @GetMapping("/pendingQueue/{doctorUserId}")
    public ResponseEntity<List<PendingQueue>> getPendingQueue(@PathVariable String doctorUserId){
        List<PendingQueue> pendingQueueList;

        try{
            pendingQueueList = pendingQueueService.getPendingQueueByDocId(doctorUserId);
        }
        catch (Exception exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(pendingQueueList));
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

    @PutMapping("/saveEncounter")
    public ResponseEntity<Encounter> completeEncounter(@RequestBody Encounter encounter){
        Encounter updatedEncounter;

        try {
            updatedEncounter = encounterService.updateEncounter(encounter);
        }catch (RuntimeException exception){
            throw exception;
        }

        try{
            followUpService.addFollowUps(encounter);
        }catch (RuntimeException exception){
            throw exception;
        }

        return ResponseEntity.of(Optional.of(updatedEncounter));
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

    @GetMapping("/getListOfFollowUpsAssignedBy/{doctorUserId}")
    public ResponseEntity<List<FollowUp>> getFollowUpsAssignedByDoctor(@PathVariable String doctorUserId){
        List<FollowUp> followUpList;
        try{
            followUpList= followUpService.getAllFollowUpsAssignedByDoctor(doctorUserId);
        }
        catch(RuntimeException exception){
            throw exception;
        }
        return ResponseEntity.of(Optional.of(followUpList));
    }
}
