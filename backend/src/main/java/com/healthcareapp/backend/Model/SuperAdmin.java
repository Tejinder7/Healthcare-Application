package com.healthcareapp.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import org.hibernate.annotations.AnyKeyJavaClass;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class SuperAdmin extends Authorization{
    public SuperAdmin() {
    }

    public SuperAdmin(int authId, String userId, String password, String userType) {
        super(authId, userId, password, userType);
    }

    @Override
    public String toString() {
        return "SuperAdmin{}";
    }
}
