package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FieldWorker extends Authorization{
    private String name;

    private String address;

    private String phoneNo;

    @ManyToOne
//    @JsonBackReference("FW-SUP")
    private Supervisor supAuthId;

    @OneToMany(mappedBy = "fieldWorkerId")
    @JsonIgnore
    private List<Patient> patientList;

    public FieldWorker() {
    }

    public FieldWorker(String name, String address, String phoneNo, Supervisor supAuthId, List<Patient> patientList) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.supAuthId = supAuthId;
        this.patientList = patientList;
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

    public Supervisor getSupAuthId() {
        return supAuthId;
    }

    public void setSupAuthId(Supervisor supAuthId) {
        this.supAuthId = supAuthId;
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
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", supAuthId=" + supAuthId +
                ", patientList=" + patientList +
                '}';
    }
}
