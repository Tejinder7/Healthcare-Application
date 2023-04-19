package com.healthcareapp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int followUpId;

    @ManyToOne
    @JoinColumn(name= "encounter_id")
//    @JsonBackReference(value="Encounter-FollowUp")
    @JsonIgnore
    private Encounter encounter;

    private String date;

    private String fieldWorkerRemarks;

    private String doctorRemarks;

    private boolean flag;

    private String lastSyncDate;

    @ManyToOne
    @JoinColumn(name= "hosp_id")
//    @JsonBackReference(value="Hospital-FollowUp")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name= "patient_id")
//    @JsonBackReference("Patient-FollowUp")
    private Patient patient;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "bloodPressure", column = @Column(name = "blood_pressure_reading")),
                    @AttributeOverride(name = "temperature", column = @Column(name = "temperature_reading")),
                    @AttributeOverride(name = "sugar", column = @Column(name = "sugar_reading"))
            }
    )
    private Readings readings;

    public FollowUp() {
    }

    public FollowUp(int followUpId, Encounter encounter, String date, String fieldWorkerRemarks, String doctorRemarks, boolean flag, String lastSyncDate, Hospital hospital, Patient patient, Readings readings) {
        this.followUpId = followUpId;
        this.encounter = encounter;
        this.date = date;
        this.fieldWorkerRemarks = fieldWorkerRemarks;
        this.doctorRemarks = doctorRemarks;
        this.flag = flag;
        this.lastSyncDate = lastSyncDate;
        this.hospital = hospital;
        this.patient = patient;
        this.readings = readings;
    }

    public int getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(int followUpId) {
        this.followUpId = followUpId;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(String lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getFieldWorkerRemarks() {
        return fieldWorkerRemarks;
    }

    public void setFieldWorkerRemarks(String fieldWorkerRemarks) {
        this.fieldWorkerRemarks = fieldWorkerRemarks;
    }

    public String getDoctorRemarks() {
        return doctorRemarks;
    }

    public void setDoctorRemarks(String doctorRemarks) {
        this.doctorRemarks = doctorRemarks;
    }

    public Readings getReadings() {
        return readings;
    }

    public void setReadings(Readings readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "FollowUp{" +
                "followUpId=" + followUpId +
                ", encounter=" + encounter +
                ", date='" + date + '\'' +
                ", fieldWorkerRemarks='" + fieldWorkerRemarks + '\'' +
                ", doctorRemarks='" + doctorRemarks + '\'' +
                ", flag=" + flag +
                ", lastSyncDate='" + lastSyncDate + '\'' +
                ", hospital=" + hospital +
                ", patient=" + patient +
                ", readings=" + readings +
                '}';
    }
}
