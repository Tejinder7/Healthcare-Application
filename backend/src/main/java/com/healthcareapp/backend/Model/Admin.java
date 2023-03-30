package com.healthcareapp.backend.Model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Admin extends Authorization{
    private String name;

    @OneToOne
    private Hospital hospId;


    public Admin() {
    }

    public Admin(String name, Hospital hospId) {
        this.name = name;
        this.hospId = hospId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hospital getHospId() {
        return hospId;
    }

    public void setHospId(Hospital hospId) {
        this.hospId = hospId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", hospId=" + hospId +
                '}';
    }
}
