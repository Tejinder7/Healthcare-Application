package com.healthcareapp.backend.Model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int followUpId;

    @ManyToOne
    @JoinColumn(name= "encounter_id")
//    @JsonBackReference(value="Encounter-FollowUp")
    private Encounter encounter;

    private String date;

    private String remarks;

    private boolean flag;

    private String lastSyncDate;

    @ManyToOne
    @JoinColumn(name= "hosp_id")
//    @JsonBackReference(value="Hospital-FollowUp")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name= "patient_id")
//    @JsonBackReference("Patient-FollowUp")
    private Patient patient;


    public FollowUp() {
    }

    public FollowUp(int followUpId, Encounter encounter, String date, String remarks, boolean flag, String lastSyncDate, Hospital hospital, Patient patient) {
        this.followUpId = followUpId;
        this.encounter = encounter;
        this.date = date;
        this.remarks = remarks;
        this.flag = flag;
        this.lastSyncDate = lastSyncDate;
        this.hospital = hospital;
        this.patient = patient;
    }

    public int getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(int followUpId) {
        this.followUpId = followUpId;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(String lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    @Override
    public String toString() {
        return "FollowUp{" +
                "followUpId=" + followUpId +
                ", encounter=" + encounter +
                ", date='" + date + '\'' +
                ", remarks='" + remarks + '\'' +
                ", flag=" + flag +
                ", lastSyncDate='" + lastSyncDate + '\'' +
                ", hospital=" + hospital +
                ", patient=" + patient +
                '}';
    }
}
