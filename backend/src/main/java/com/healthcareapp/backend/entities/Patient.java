package com.healthcareapp.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;

    private String name;

    private String address;

    private int age;

    private String sex;

    private String contact;

    @OneToMany(mappedBy = "patientId")
    @JsonManagedReference(value = "Patient-MedicalHistory")
    private List<MedicalHistory> medicalHistoryList;

    @ManyToOne
    @JsonBackReference(value = "FieldWorker-Patient")
    private FieldWorker fieldWorkerId;

    @OneToOne(mappedBy = "patientId")
    @JsonManagedReference(value = "Patient-PendingQueue")
    private PendingQueue pendingQueueId;

    @OneToMany(mappedBy = "patientId")
    @JsonManagedReference(value = "Encounter-Patient")
    private List<Encounter> encounterList;

    @OneToMany(mappedBy = "patientId")
    @JsonManagedReference(value="Patient-FollowUp")
    private List<FollowUp> followUpList;

    public Patient() {
    }

    public Patient(int patientId, String name, String address, int age, String sex, String contact, List<MedicalHistory> medicalHistoryList, FieldWorker fieldWorkerId, PendingQueue pendingQueueId, List<Encounter> encounterList, List<FollowUp> followUpList) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.medicalHistoryList = medicalHistoryList;
        this.fieldWorkerId = fieldWorkerId;
        this.pendingQueueId = pendingQueueId;
        this.encounterList = encounterList;
        this.followUpList = followUpList;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<MedicalHistory> getMedicalHistoryList() {
        return medicalHistoryList;
    }

    public void setMedicalHistoryList(List<MedicalHistory> medicalHistoryList) {
        this.medicalHistoryList = medicalHistoryList;
    }

    public FieldWorker getFieldWorkerId() {
        return fieldWorkerId;
    }

    public void setFieldWorkerId(FieldWorker fieldWorkerId) {
        this.fieldWorkerId = fieldWorkerId;
    }

    public PendingQueue getPendingQueueId() {
        return pendingQueueId;
    }

    public void setPendingQueueId(PendingQueue pendingQueueId) {
        this.pendingQueueId = pendingQueueId;
    }

    public List<Encounter> getEncounterList() {
        return encounterList;
    }

    public void setEncounterList(List<Encounter> encounterList) {
        this.encounterList = encounterList;
    }

    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", medicalHistoryList=" + medicalHistoryList +
                ", fieldWorkerId=" + fieldWorkerId +
                ", pendingQueueId=" + pendingQueueId +
                ", encounterList=" + encounterList +
                ", followUpList=" + followUpList +
                '}';
    }
}
