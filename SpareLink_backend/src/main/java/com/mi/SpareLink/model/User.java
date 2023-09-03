package com.mi.SpareLink.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Userid;

    private String username;
    private String Password;
    private Long RoleID;

    // Constructors, getters, setters

    public Long getUserid() {
        return Userid;
    }

    public void setUserid(Long Userid) {
        this.Userid = Userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Long getRoleID() {
        return RoleID;
    }

    public void setRoleID(Long RoleID) {
        this.RoleID = RoleID;
    }
}
