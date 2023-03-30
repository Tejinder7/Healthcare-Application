package com.healthcareapp.backend.Model;

import jakarta.persistence.*;

@Entity
public class PendingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pendingQueueId;

    private String dateTime;


    @OneToOne
    @JoinColumn(name= "patient_id", nullable = false)
//    @JsonBackReference(value = "Patient-PendingQueue")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "hosp_id", nullable = false)
//    @JsonBackReference(value = "Hospital-PendingQueue")
    private Hospital hospital;

    public PendingQueue() {
    }

    public PendingQueue(int pendingQueueId, String dateTime, Patient patient, Hospital hospital) {
        this.pendingQueueId = pendingQueueId;
        this.dateTime = dateTime;
        this.patient = patient;
        this.hospital = hospital;
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

    @Override
    public String toString() {
        return "PendingQueue{" +
                "pendingQueueId=" + pendingQueueId +
                ", dateTime='" + dateTime + '\'' +
                ", patient=" + patient +
                ", hospital=" + hospital +
                '}';
    }
}
