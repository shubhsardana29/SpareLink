package com.mi.SpareLink.dto;

public class UserDTO {

    private Long Userid;
    private String username;
    private Long RoleID;

    public Long getId() {
        return Userid;
    }

    public void setId(Long Userid) {
        this.Userid = Userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRole() {
        return RoleID;
    }

    public void setRole(Long RoleID) {
        this.RoleID = RoleID;
    }

}
