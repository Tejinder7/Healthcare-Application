package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@JsonFilter("MedicalHistoryFilter")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int medicalHistoryId;

    @ManyToOne
    @JoinColumn(name= "patient_id")
//    @JsonBackReference(value = "Patient-MedicalHistory")
    private Patient patient;

    private String symptoms;

    private String prescription;

    @OneToOne(mappedBy = "medicalHistory")
//    @JsonBackReference(value = "Encounter-MH")
    @JsonIgnore
    @JoinColumn(unique = true)
    private Encounter encounter;

    public MedicalHistory() {
    }

    public MedicalHistory(int medicalHistoryId, Patient patient, String symptoms, String prescription, Encounter encounter) {
        this.medicalHistoryId = medicalHistoryId;
        this.patient = patient;
        this.symptoms = symptoms;
        this.prescription = prescription;
        this.encounter = encounter;
    }

    public int getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(int medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "medicalHistoryId=" + medicalHistoryId +
                ", patient=" + patient +
                ", symptoms='" + symptoms + '\'' +
                ", prescription='" + prescription + '\'' +
                ", encounter=" + encounter +
                '}';
    }
}
