package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Hospital {
    @Id
    private int hosp_id;

    private String hosp_name;

    private String hosp_address;

    @OneToMany(mappedBy = "hospital")
    @JsonBackReference
    private List<Doctor> doctorList;

    //Need to add admin id and list of front desk and pending queue


    public Hospital() {
    }

    public Hospital(int hosp_id, String hosp_name, String hosp_address, List<Doctor> doctorList) {
        this.hosp_id = hosp_id;
        this.hosp_name = hosp_name;
        this.hosp_address = hosp_address;
        this.doctorList = doctorList;
    }

    public int getHosp_id() {
        return hosp_id;
    }

    public void setHosp_id(int hosp_id) {
        this.hosp_id = hosp_id;
    }

    public String getHosp_name() {
        return hosp_name;
    }

    public void setHosp_name(String hosp_name) {
        this.hosp_name = hosp_name;
    }

    public String getHosp_address() {
        return hosp_address;
    }

    public void setHosp_address(String hosp_address) {
        this.hosp_address = hosp_address;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hosp_id=" + hosp_id +
                ", hosp_name='" + hosp_name + '\'' +
                ", hosp_address='" + hosp_address + '\'' +
                ", doctorList=" + doctorList +
                '}';
    }
}
