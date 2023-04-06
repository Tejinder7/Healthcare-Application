package com.healthcareapp.backend.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnore
    private List<FollowUp> followUpList;

    @OneToOne
    @JoinColumn(name= "medicalhistory_id", unique = true)
//    @JsonManagedReference(value = "Encounter-MH")
    private MedicalHistory medicalHistory;

    @ManyToOne
    @JoinColumn(name= "patient_id")
//    @JsonBackReference(value = "Encounter-Patient")
    private Patient patient;

    public Encounter() {
    }

    public Encounter(int encounterId, Doctor doctor, List<FollowUp> followUpList, MedicalHistory medicalHistory, Patient patient, Boolean flag) {
        this.encounterId = encounterId;
        this.doctor = doctor;
        this.followUpList = followUpList;
        this.medicalHistory = medicalHistory;
        this.patient = patient;
        this.flag = flag;
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

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
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
                ", doctor=" + doctor +
                ", followUpList=" + followUpList +
                ", medicalHistory=" + medicalHistory +
                ", patient=" + patient +
                ", flag=" + flag +
                '}';
    }
}
