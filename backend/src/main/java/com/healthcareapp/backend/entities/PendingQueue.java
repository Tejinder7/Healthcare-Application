package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

@Entity
public class PendingQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pen_id;

    private String date_time;

    @OneToOne
    @JoinColumn(name= "patient_id", referencedColumnName = "pid", unique = true)
    private Patient patient;

    @OneToOne
    private Hospital hospital;


    public PendingQueue() {
    }

    public PendingQueue(int pen_id, String date_time, Patient patient, Hospital hospital) {
        this.pen_id = pen_id;
        this.date_time = date_time;
        this.patient = patient;
        this.hospital = hospital;
    }

    public int getPen_id() {
        return pen_id;
    }

    public void setPen_id(int pen_id) {
        this.pen_id = pen_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
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
                "pen_id=" + pen_id +
                ", date_time='" + date_time + '\'' +
                ", patient=" + patient +
                ", hospital=" + hospital +
                '}';
    }
}
