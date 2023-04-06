package com.healthcareapp.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Admin extends Authorization{
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
