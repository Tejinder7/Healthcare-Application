package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FieldWorker extends Authorization{

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fwId;

    private String name;

    private String address;

    private String phoneNo;

    @ManyToOne
    @JsonBackReference("FW-SUP")
    private Supervisor supId;

    @OneToMany(mappedBy = "fieldWorkerId")
    @JsonManagedReference(value = "FieldWorker-Patient")
    private List<Patient> patientList;

    public FieldWorker() {
    }

    public FieldWorker(int fwId, String name, String address, String phoneNo, Supervisor supId, List<Patient> patientList) {
        this.fwId = fwId;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.supId = supId;
        this.patientList = patientList;
    }

    public int getFwId() {
        return fwId;
    }

    public void setFwId(int fwId) {
        this.fwId = fwId;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Supervisor getSupId() {
        return supId;
    }

    public void setSupId(Supervisor supId) {
        this.supId = supId;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @Override
    public String toString() {
        return "FieldWorker{" +
                "fwId=" + fwId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", supId=" + supId +
                ", patientList=" + patientList +
                '}';
    }
}
