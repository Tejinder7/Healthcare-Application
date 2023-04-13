package com.healthcareapp.backend.Model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FrontDesk extends Authorization{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name= "hosp_id")
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
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
