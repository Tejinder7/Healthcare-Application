package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Supervisor extends Authorization{
    private String name;

    private String contact;
    private String address;

    @Column(unique = true)
    private int pincode;

    @OneToMany(mappedBy = "supervisor")
//    @JsonManagedReference("FW-SUP")
    @JsonIgnore
    private List<FieldWorker> fieldWorkerList;

    @OneToMany(mappedBy = "supervisor")
    @JsonIgnore
    private List<Hospital> hospitalList;

    public Supervisor() {
    }

    public Supervisor(String name, String contact, String address, int pincode, List<FieldWorker> fieldWorkerList, List<Hospital> hospitalList) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.pincode = pincode;
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", pincode=" + pincode +
                ", fieldWorkerList=" + fieldWorkerList +
                ", hospitalList=" + hospitalList +
                '}';
    }
}
