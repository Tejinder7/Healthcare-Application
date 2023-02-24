package com.healthcareapp.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pid;

    private String name;

    private String address;

    private int age;

    private String sex;

    private String contact;

    public Patient() {
    }

    public Patient(int p_id, String name, String address, int age, String sex, String contact) {
        pid = p_id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
    }

    public int getP_id() {
        return pid;
    }

    public void setP_id(int p_id) {
        pid = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "P_id=" + pid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
