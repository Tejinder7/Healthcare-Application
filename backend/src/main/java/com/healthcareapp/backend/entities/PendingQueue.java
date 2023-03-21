package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

@Entity
public class PendingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int penId;

    private String dateTime;

    @OneToOne(mappedBy = "pendingQueueId")
    private Patient patientId;

    @ManyToOne
    private Hospital hospId;

    public PendingQueue() {
    }

    public PendingQueue(int penId, String dateTime, Patient patientId, Hospital hospId) {
        this.penId = penId;
        this.dateTime = dateTime;
        this.patientId = patientId;
        this.hospId = hospId;
    }

    public int getPenId() {
        return penId;
    }

    public void setPenId(int penId) {
        this.penId = penId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Hospital getHospId() {
        return hospId;
    }

    public void setHospId(Hospital hospId) {
        this.hospId = hospId;
    }

    @Override
    public String toString() {
        return "PendingQueue{" +
                "penId=" + penId +
                ", dateTime='" + dateTime + '\'' +
                ", patientId=" + patientId +
                ", hospId=" + hospId +
                '}';
    }
}
