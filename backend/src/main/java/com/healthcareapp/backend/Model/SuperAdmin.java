package com.healthcareapp.backend.Model;

import com.healthcareapp.backend.Security.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "authId")
public class SuperAdmin extends Authorization{
    public SuperAdmin() {
    }

    public SuperAdmin(int authId, String username, String password, Role role, List<Token> tokens) {
        super(authId, username, password, role, tokens);
    }

    @Override
    public String toString() {
        return "SuperAdmin{}";
    }
}
