package com.healthcareapp.backend.Model;


import jakarta.persistence.*;

import java.util.List;


@Entity
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int encounterId;

    private Boolean flag;

    @ManyToOne
    @JoinColumn(name= "doctor_id")
//    @JsonBackReference(value = "Encounter-Doctor")
    private Doctor doctor;

    @OneToMany(mappedBy = "encounter")
//    @JsonManagedReference(value = "Encounter-FollowUp")
//    @JsonIgnore
    private List<FollowUp> followUpList;

    private String prescription;

    private String symptoms;

    @ManyToOne
    @JoinColumn(name= "patient_id")
//    @JsonBackReference(value = "Encounter-Patient")
    private Patient patient;

    public Encounter() {
    }

    public Encounter(int encounterId, Boolean flag, Doctor doctor, List<FollowUp> followUpList, String prescription, String symptoms, Patient patient) {
        this.encounterId = encounterId;
        this.flag = flag;
        this.doctor = doctor;
        this.followUpList = followUpList;
        this.prescription = prescription;
        this.symptoms = symptoms;
        this.patient = patient;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Encounter{" +
                "encounterId=" + encounterId +
                ", flag=" + flag +
                ", doctor=" + doctor +
                ", followUpList=" + followUpList +
                ", prescription='" + prescription + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", patient=" + patient +
                '}';
    }
}
