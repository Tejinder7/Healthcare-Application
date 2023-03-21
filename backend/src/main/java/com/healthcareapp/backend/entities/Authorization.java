package com.healthcareapp.backend.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authId;

    private String userId;

    private String password;

    private String userType;


    public Authorization() {
    }

    public Authorization(int authId, String userId, String password, String userType) {
        this.authId = authId;
        this.userId = userId;
        this.password = password;
        this.userType = userType;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "authId=" + authId +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
