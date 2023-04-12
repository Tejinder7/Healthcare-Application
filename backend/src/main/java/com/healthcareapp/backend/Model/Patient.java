package com.healthcareapp.backend.Model;


import com.fasterxml.jackson.annotation.*;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;

    @Column(nullable = false)
    private String name;

    private String address;

    private int pincode;


    private String DOB;


    private int age;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String contact;

    @ManyToOne
    @JoinColumn(name= "field_worker_id")
//    @JsonBackReference(value = "FieldWorker-Patient")
    private FieldWorker fieldWorker;

    @OneToOne(mappedBy = "patient")
//    @JsonManagedReference(value = "Patient-PendingQueue")
    @JsonIgnore
    @JoinColumn()
    private PendingQueue pendingQueue;

    @OneToMany(mappedBy = "patient")
//    @JsonManagedReference(value = "Encounter-Patient")
    @JsonIgnore
    private List<Encounter> encounterList;

    @OneToMany(mappedBy = "patient")
//    @JsonManagedReference(value="Patient-FollowUp")
    @JsonIgnore
    private List<FollowUp> followUpList;

    public Patient() {
    }

    public Patient(int patientId, String name, String address, int pincode, String DOB, int age, String sex, String contact, FieldWorker fieldWorker, PendingQueue pendingQueue, List<Encounter> encounterList, List<FollowUp> followUpList) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.pincode = pincode;
        this.DOB = DOB;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.fieldWorker = fieldWorker;
        this.pendingQueue = pendingQueue;
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

    public FieldWorker getFieldWorker() {
        return fieldWorker;
    }

    public void setFieldWorker(FieldWorker fieldWorker) {
        this.fieldWorker = fieldWorker;
    }

    public PendingQueue getPendingQueue() {
        return pendingQueue;
    }

    public void setPendingQueue(PendingQueue pendingQueue) {
        this.pendingQueue = pendingQueue;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                ", DOB='" + DOB + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", fieldWorker=" + fieldWorker +
                ", pendingQueue=" + pendingQueue +
                ", encounterList=" + encounterList +
                ", followUpList=" + followUpList +
                '}';
    }
}
