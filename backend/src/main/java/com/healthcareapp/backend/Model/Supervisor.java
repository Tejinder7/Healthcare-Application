package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Supervisor extends Authorization{
    private String name;

    private String contact;

    private String address;

    @OneToMany(mappedBy = "supAuthId")
//    @JsonManagedReference("FW-SUP")
    @JsonIgnore
    private List<FieldWorker> fieldWorkerList;

    @OneToMany(mappedBy = "supId")
    @JsonIgnore
    private List<Hospital> hospitalList;

    public Supervisor() {
    }

    public Supervisor(String name, String contact, String address, List<FieldWorker> fieldWorkerList, List<Hospital> hospitalList) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.fieldWorkerList = fieldWorkerList;
        this.hospitalList = hospitalList;
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
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", fieldWorkerList=" + fieldWorkerList +
                ", hospitalList=" + hospitalList +
                '}';
    }
}
