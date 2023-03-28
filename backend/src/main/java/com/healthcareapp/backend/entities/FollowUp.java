package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int followUpId;

    @ManyToOne
    @JsonBackReference(value="Encounter-FollowUp")
    private Encounter encounterId;

    private String date;

    private String remarks;

    private boolean flag;

    private String lastSyncDate;

    @ManyToOne
    @JsonBackReference(value="Hospital-FollowUp")
    private Hospital hospId;

    @ManyToOne
    @JsonBackReference("Patient-FollowUp")
    private Patient patientId;


    public FollowUp() {
    }

    public FollowUp(int followUpId, Encounter encounterId, String date, String remarks, boolean flag, String lastSyncDate, Hospital hospId, Patient patientId) {
        this.followUpId = followUpId;
        this.encounterId = encounterId;
        this.date = date;
        this.remarks = remarks;
        this.flag = flag;
        this.lastSyncDate = lastSyncDate;
        this.hospId = hospId;
        this.patientId = patientId;
    }

    public int getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(int followUpId) {
        this.followUpId = followUpId;
    }

    public Encounter getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Encounter encounterId) {
        this.encounterId = encounterId;
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

    public Hospital getHospId() {
        return hospId;
    }

    public void setHospId(Hospital hospId) {
        this.hospId = hospId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "FollowUp{" +
                "followUpId=" + followUpId +
                ", encounterId=" + encounterId +
                ", date='" + date + '\'' +
                ", remarks='" + remarks + '\'' +
                ", flag=" + flag +
                ", lastSyncDate='" + lastSyncDate + '\'' +
                ", hospId=" + hospId +
                ", patientId=" + patientId +
                '}';
    }
}
