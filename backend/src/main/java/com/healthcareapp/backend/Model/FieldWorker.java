package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FieldWorker extends Authorization{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int pincode;

    @Column(nullable = false)
    private String contact;

    private boolean availableStatus;

    @ManyToOne
    @JoinColumn(name= "supervisor_id")
//    @JsonBackReference("FW-SUP")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "fieldWorker")
    @JsonIgnore
    private List<Patient> patientList;

    public FieldWorker() {
    }


    public FieldWorker(String name, String address, int pincode, String contact, boolean availableStatus, Supervisor supervisor, List<Patient> patientList) {
        this.name = name;
        this.address = address;
        this.pincode = pincode;
        this.contact = contact;
        this.availableStatus = availableStatus;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String phoneNo) {
        this.contact = phoneNo;
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

    public boolean isAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(boolean availableStatus) {
        this.availableStatus = availableStatus;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "FieldWorker{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                ", contact='" + contact + '\'' +
                ", availableStatus=" + availableStatus +
                ", supervisor=" + supervisor +
                ", patientList=" + patientList +
                '}';
    }
}
