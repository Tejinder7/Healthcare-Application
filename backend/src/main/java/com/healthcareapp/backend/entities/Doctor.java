package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.beans.Encoder;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Doctor extends Authorization{

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int docId;

    private String name;

    private String licId;

    private String phoneNum;

    private String docSpecialization;

    @ManyToOne
    private Hospital hospId;

    @OneToMany(mappedBy = "doctorId")
    @JsonManagedReference(value = "Encounter-Doctor")
    private List<Encounter> encounterList;

    public Doctor() {
    }

    public Doctor(int docId, String name, String licId, String phoneNum, String docSpecialization, Hospital hospId, List<Encounter> encounterList) {
        this.docId = docId;
        this.name = name;
        this.licId = licId;
        this.phoneNum = phoneNum;
        this.docSpecialization = docSpecialization;
        this.hospId = hospId;
        this.encounterList = encounterList;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicId() {
        return licId;
    }

    public void setLicId(String licId) {
        this.licId = licId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDocSpecialization() {
        return docSpecialization;
    }

    public void setDocSpecialization(String docSpecialization) {
        this.docSpecialization = docSpecialization;
    }

    public Hospital getHospId() {
        return hospId;
    }

    public void setHospId(Hospital hospId) {
        this.hospId = hospId;
    }

    public List<Encounter> getEncounterList() {
        return encounterList;
    }

    public void setEncounterList(List<Encounter> encounterList) {
        this.encounterList = encounterList;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "docId=" + docId +
                ", name='" + name + '\'' +
                ", licId='" + licId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", docSpecialization='" + docSpecialization + '\'' +
                ", hospId=" + hospId +
                ", encounterList=" + encounterList +
                '}';
    }
}
