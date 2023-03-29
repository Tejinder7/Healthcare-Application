package com.healthcareapp.backend.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hospId;

    private String name;

    private String address;

    @OneToOne(mappedBy = "hospId")
    @JsonIgnore
    private Admin admin;

    @ManyToOne
    private Supervisor supId;

    @OneToMany(mappedBy = "hospId")
    @JsonIgnore
    private List<FrontDesk> frontDeskList;
    
    @OneToMany(mappedBy = "hospId")
    @JsonIgnore
    private List<Doctor> doctorList;
    

    @OneToMany(mappedBy = "hospId")
//    @JsonManagedReference(value = "Hospital-PendingQueue")
    @JsonIgnore
    private List<PendingQueue> pendingQueueList;

    @OneToMany(mappedBy = "hospId")
//    @JsonManagedReference(value = "Hospital-FollowUp")
    @JsonIgnore
    private List<FollowUp> followUpList;


    public Hospital() {
    }

    public Hospital(int hospId, String name, String address, Admin admin, Supervisor supId, List<FrontDesk> frontDeskList, List<Doctor> doctorList, List<PendingQueue> pendingQueueList, List<FollowUp> followUpList) {
        this.hospId = hospId;
        this.name = name;
        this.address = address;
        this.admin = admin;
        this.supId = supId;
        this.frontDeskList = frontDeskList;
        this.doctorList = doctorList;
        this.pendingQueueList = pendingQueueList;
        this.followUpList = followUpList;
    }

    public int getHospId() {
        return hospId;
    }

    public void setHospId(int hospId) {
        this.hospId = hospId;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Supervisor getSupId() {
        return supId;
    }

    public void setSupId(Supervisor supId) {
        this.supId = supId;
    }

    public List<FrontDesk> getFrontDeskList() {
        return frontDeskList;
    }

    public void setFrontDeskList(List<FrontDesk> frontDeskList) {
        this.frontDeskList = frontDeskList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<PendingQueue> getPendingQueueList() {
        return pendingQueueList;
    }

    public void setPendingQueueList(List<PendingQueue> pendingQueueList) {
        this.pendingQueueList = pendingQueueList;
    }

    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospId=" + hospId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", admin=" + admin +
                ", supId=" + supId +
                ", frontDeskList=" + frontDeskList +
                ", doctorList=" + doctorList +
                ", pendingQueueList=" + pendingQueueList +
                ", followUpList=" + followUpList +
                '}';
    }
}
