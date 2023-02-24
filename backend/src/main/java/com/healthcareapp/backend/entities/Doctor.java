package com.healthcareapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Doctor {
    @Id
    private int doc_id;

    private String doc_name;

    private String license_id;

    private String contact;

    private String specialisation;

    @ManyToOne
    @JoinColumn(name= "hospital", nullable = false)
    @JsonManagedReference
    private Hospital hospital;

    public Doctor() {
    }

    public Doctor(int doc_id, String doc_name, String license_id, String contact, String specialisation, Hospital hospital) {
        this.doc_id = doc_id;
        this.doc_name = doc_name;
        this.license_id = license_id;
        this.contact = contact;
        this.specialisation = specialisation;
        this.hospital = hospital;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getLicense_id() {
        return license_id;
    }

    public void setLicense_id(String license_id) {
        this.license_id = license_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doc_id=" + doc_id +
                ", doc_name='" + doc_name + '\'' +
                ", license_id='" + license_id + '\'' +
                ", contact='" + contact + '\'' +
                ", specialisation='" + specialisation + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
