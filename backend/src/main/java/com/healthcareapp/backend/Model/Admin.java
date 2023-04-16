package com.healthcareapp.backend.Model;

import jakarta.persistence.*;
@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Admin extends Authorization{

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name= "hosp_id", unique = true)
    private Hospital hospital;

    public Admin() {
    }

    public Admin(String name, Hospital hospital) {
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
        return "Admin{" +
                "name='" + name + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
