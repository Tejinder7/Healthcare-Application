package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class FrontDesk extends Authorization{
    private String name;

    @ManyToOne
//    @JsonBackReference("HOS-FD")
    private Hospital hospId;

    public FrontDesk() {
    }

    public FrontDesk(String name, Hospital hospId) {
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
        return "FrontDesk{" +
                "name='" + name + '\'' +
                ", hospId=" + hospId +
                '}';
    }
}
