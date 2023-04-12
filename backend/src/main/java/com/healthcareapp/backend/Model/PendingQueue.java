package com.healthcareapp.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class PendingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pendingQueueId;

    @Column(nullable = false)
    private String dateTime;

    private Boolean flag;

    @OneToOne
    @JoinColumn(name= "patient_id", unique = true)
//    @JsonBackReference(value = "Patient-PendingQueue")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "hosp_id")
//    @JsonBackReference(value = "Hospital-PendingQueue")
    private Hospital hospital;

    public PendingQueue() {
    }

    public PendingQueue(int pendingQueueId, String dateTime, Patient patient, Hospital hospital, Boolean flag) {
        this.pendingQueueId = pendingQueueId;
        this.dateTime = dateTime;
        this.patient = patient;
        this.hospital = hospital;
        this.flag = flag;
    }

    public int getPendingQueueId() {
        return pendingQueueId;
    }

    public void setPendingQueueId(int pendingQueueId) {
        this.pendingQueueId = pendingQueueId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "PendingQueue{" +
                "pendingQueueId=" + pendingQueueId +
                ", dateTime='" + dateTime + '\'' +
                ", patient=" + patient +
                ", hospital=" + hospital +
                ", flag=" + flag +
                '}';
    }
}
