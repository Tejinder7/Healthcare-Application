package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class PendingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int penId;

    private String dateTime;


    @OneToOne
//    @JsonBackReference(value = "Patient-PendingQueue")
    private Patient patientId;

    @ManyToOne
//    @JsonBackReference(value = "Hospital-PendingQueue")
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
