package com.healthcareapp.backend.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Readings {
    private String bloodPressure;

    private String temperature;

    private String sugar;

    public Readings() {
    }

    public Readings(String bloodPressure, String temperature, String sugar) {
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.sugar = sugar;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    @Override
    public String toString() {
        return "Readings{" +
                "bloodPressure='" + bloodPressure + '\'' +
                ", temperature='" + temperature + '\'' +
                ", sugar='" + sugar + '\'' +
                '}';
    }
}
