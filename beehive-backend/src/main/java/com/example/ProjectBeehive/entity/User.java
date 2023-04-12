package com.example.ProjectBeehive.entity;

//import com.example.ProjectBeehive.security.UserRole;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private BigInteger ID;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(name = "ROLES")
    private String ROLES;


    @Column(nullable = false)
    private String F_NAME;


    @Column(nullable = false)
    private String L_NAME;

    @Column(nullable = false)
    private String EMAIL;

    public User() {
        this.ROLES = "";
    }

    public User(BigInteger ID, String username, String password, String ROLES, String F_NAME, String L_NAME, String EMAIL) {
        this.F_NAME = F_NAME;
        this.L_NAME = L_NAME;
        this.EMAIL = EMAIL;
        this.ROLES = ROLES;
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    // getters and setters

    public BigInteger getId() {
        return ID;
    }

    public void setId(BigInteger ID) {
        this.ID = ID;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){ return this.username; }

    public void setPassword(String password){ this.password = password;}
    public String getPassword(){ return this.password; }

    public String getFName() {
        return F_NAME;
    }

    public void setFName(String F_NAME) {
        this.F_NAME = F_NAME;
    }
    public String getLName() {
        return L_NAME;
    }

    public void setLName(String L_NAME) {
        this.L_NAME = L_NAME;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    //public UserRole[] getRoles() { return UserRole.values(); }

    public void setRoles(String ROLES) {
        this.ROLES = ROLES;
    }

    //public void login()

}
