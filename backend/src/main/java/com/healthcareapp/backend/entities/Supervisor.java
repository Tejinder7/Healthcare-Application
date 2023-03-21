package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Supervisor extends Authorization{

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int supId;

    private String name;

    private String contact;

    private String address;

    @OneToMany(mappedBy = "supId")
    private List<FieldWorker> fieldWorkerList;

    @OneToMany(mappedBy = "supId")
    private List<Hospital> hospitalList;

    public Supervisor() {
    }

    public Supervisor(int supId, String name, String contact, String address, List<FieldWorker> fieldWorkerList, List<Hospital> hospitalList) {
        this.supId = supId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.fieldWorkerList = fieldWorkerList;
        this.hospitalList = hospitalList;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FieldWorker> getFieldWorkerList() {
        return fieldWorkerList;
    }

    public void setFieldWorkerList(List<FieldWorker> fieldWorkerList) {
        this.fieldWorkerList = fieldWorkerList;
    }

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "supId=" + supId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", fieldWorkerList=" + fieldWorkerList +
                ", hospitalList=" + hospitalList +
                '}';
    }
}
