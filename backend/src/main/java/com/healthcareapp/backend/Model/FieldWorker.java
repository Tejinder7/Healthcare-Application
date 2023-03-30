package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FieldWorker extends Authorization{
    private String name;

    private String address;

    private String phoneNo;

    @ManyToOne
    @JoinColumn(name= "supervisor_id", nullable = false)
//    @JsonBackReference("FW-SUP")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "fieldWorker")
    @JsonIgnore
    private List<Patient> patientList;

    public FieldWorker() {
    }

    public FieldWorker(String name, String address, String phoneNo, Supervisor supervisor, List<Patient> patientList) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.supervisor = supervisor;
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

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
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
                ", supervisor=" + supervisor +
                ", patientList=" + patientList +
                '}';
    }
}
