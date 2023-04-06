package com.healthcareapp.backend.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int hospId;

    private String name;

    private String address;

    @OneToOne(mappedBy = "hospital")
    @JsonIgnore
    @JoinColumn(unique = true)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name= "supervisor_id")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "hospital")
    @JsonIgnore
    private List<FrontDesk> frontDeskList;
    
    @OneToMany(mappedBy = "hospital")
    @JsonIgnore
    private List<Doctor> doctorList;
    

    @OneToMany(mappedBy = "hospital")
//    @JsonManagedReference(value = "Hospital-PendingQueue")
    @JsonIgnore
    private List<PendingQueue> pendingQueueList;

    @OneToMany(mappedBy = "hospital")
//    @JsonManagedReference(value = "Hospital-FollowUp")
    @JsonIgnore
    private List<FollowUp> followUpList;


    public Hospital() {
    }

    public Hospital(int hospId, String name, String address, Admin admin, Supervisor supervisor, List<FrontDesk> frontDeskList, List<Doctor> doctorList, List<PendingQueue> pendingQueueList, List<FollowUp> followUpList) {
        this.hospId = hospId;
        this.name = name;
        this.address = address;
        this.admin = admin;
        this.supervisor = supervisor;
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
        return supervisor;
    }

    public void setSupId(Supervisor supervisor) {
        this.supervisor = supervisor;
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
                ", supervisor=" + supervisor +
                ", frontDeskList=" + frontDeskList +
                ", doctorList=" + doctorList +
                ", pendingQueueList=" + pendingQueueList +
                ", followUpList=" + followUpList +
                '}';
    }
}
