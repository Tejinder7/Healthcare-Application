package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class Admin extends Authorization{

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;

    private String name;

    @OneToOne
    private Hospital hospId;


    public Admin() {
    }

    public Admin(int adminId, String name, Hospital hospId) {
        this.adminId = adminId;
        this.name = name;
        this.hospId = hospId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", hospId=" + hospId +
                '}';
    }
}
