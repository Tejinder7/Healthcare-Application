package com.healthcareapp.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Hospital {
    @Id
    private int hospId;

    private String hosp_name;

    private String hosp_address;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctorList;

    //Need to add admin id and list of front desk and pending queue


    public Hospital() {
    }

    public Hospital(int hosp_id, String hosp_name, String hosp_address, List<Doctor> doctorList) {
        this.hospId = hosp_id;
        this.hosp_name = hosp_name;
        this.hosp_address = hosp_address;
        this.doctorList = doctorList;
    }

    public int getHosp_id() {
        return hospId;
    }

    public void setHosp_id(int hosp_id) {
        this.hospId = hosp_id;
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
                "hosp_id=" + hospId +
                ", hosp_name='" + hosp_name + '\'' +
                ", hosp_address='" + hosp_address + '\'' +
                ", doctorList=" + doctorList +
                '}';
    }
}
