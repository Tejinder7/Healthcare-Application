package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Doctor extends Authorization{

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String licId;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String docSpecialization;

    @ManyToOne
    @JoinColumn(name= "hosp_id")
//    @JsonBackReference("HOS-DOC")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
//    @JsonManagedReference(value = "Encounter-Doctor")
    @JsonIgnore
    private List<Encounter> encounterList;

    public Doctor() {
    }

    public Doctor(String name, String licId, String contact, String docSpecialization, Hospital hospital, List<Encounter> encounterList) {
        this.name = name;
        this.licId = licId;
        this.contact = contact;
        this.docSpecialization = docSpecialization;
        this.hospital = hospital;
        this.encounterList = encounterList;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String phoneNum) {
        this.contact = phoneNum;
    }

    public String getDocSpecialization() {
        return docSpecialization;
    }

    public void setDocSpecialization(String docSpecialization) {
        this.docSpecialization = docSpecialization;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
                "name='" + name + '\'' +
                ", licId='" + licId + '\'' +
                ", phoneNum='" + contact + '\'' +
                ", docSpecialization='" + docSpecialization + '\'' +
                ", hospital=" + hospital +
                ", encounterList=" + encounterList +
                '}';
    }
}
