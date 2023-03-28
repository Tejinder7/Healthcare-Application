package com.healthcareapp.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int encounterId;

    @ManyToOne
    @JsonBackReference(value = "Encounter-Doctor")
    private Doctor doctorId;

    @OneToMany(mappedBy = "encounterId")
    @JsonManagedReference(value = "Encounter-FollowUp")
    private List<FollowUp> followUpList;

    @OneToOne
    @JsonManagedReference(value = "Encounter-MH")
    private MedicalHistory medicalHistoryId;

    @ManyToOne
    @JsonBackReference(value = "Encounter-Patient")
    private Patient patientId;

    public Encounter() {
    }

    public Encounter(int encounterId, Doctor doctorId, List<FollowUp> followUpList, MedicalHistory medicalHistoryId, Patient patientId) {
        this.encounterId = encounterId;
        this.doctorId = doctorId;
        this.followUpList = followUpList;
        this.medicalHistoryId = medicalHistoryId;
        this.patientId = patientId;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
    }

    public MedicalHistory getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(MedicalHistory medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Encounter{" +
                "encounterId=" + encounterId +
                ", doctorId=" + doctorId +
                ", followUpList=" + followUpList +
                ", medicalHistoryId=" + medicalHistoryId +
                ", patientId=" + patientId +
                '}';
    }
}
