package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FrontDesk extends Authorization{

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fDeskId;

    private String name;

    @ManyToOne
    @JsonBackReference("HOS-FD")
    private Hospital hospId;

    public FrontDesk() {
    }

    public FrontDesk(int fDeskId, String name, Hospital hospId) {
        this.fDeskId = fDeskId;
        this.name = name;
        this.hospId = hospId;
    }

    public int getfDeskId() {
        return fDeskId;
    }

    public void setfDeskId(int fDeskId) {
        this.fDeskId = fDeskId;
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
        return "FrontDesk{" +
                "fDeskId=" + fDeskId +
                ", name='" + name + '\'' +
                ", hospId=" + hospId +
                '}';
    }
}
