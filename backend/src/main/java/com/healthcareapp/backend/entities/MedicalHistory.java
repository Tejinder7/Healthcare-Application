package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int medicalHistoryId;

    @ManyToOne
    private Patient patientId;

    private String symptoms;

    private String prescription;

    @OneToOne(mappedBy = "medicalHistoryId")
    private Encounter encounterId;

    public MedicalHistory() {
    }

    public MedicalHistory(int medicalHistoryId, Patient patientId, String symptoms, String prescription, Encounter encounterId) {
        this.medicalHistoryId = medicalHistoryId;
        this.patientId = patientId;
        this.symptoms = symptoms;
        this.prescription = prescription;
        this.encounterId = encounterId;
    }

    public int getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(int medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Encounter getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Encounter encounterId) {
        this.encounterId = encounterId;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "medicalHistoryId=" + medicalHistoryId +
                ", patientId=" + patientId +
                ", symptoms='" + symptoms + '\'' +
                ", prescription='" + prescription + '\'' +
                ", encounterId=" + encounterId +
                '}';
    }
}
