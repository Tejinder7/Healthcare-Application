package com.healthcareapp.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FrontDesk extends Authorization{
    private String name;

    @ManyToOne
    @JoinColumn(name= "hosp_id", nullable = false)
//    @JsonBackReference("HOS-FD")
    private Hospital hospital;

    public FrontDesk() {
    }

    public FrontDesk(String name, Hospital hospital) {
        this.name = name;
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hospital getHospId() {
        return hospital;
    }

    public void setHospId(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "FrontDesk{" +
                "name='" + name + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
