// package com.mi.SpareLink.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import java.util.Set;

// @Entity
// public class Permission {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long PermissionID;

//     private String PermissionName; // Make sure this matches the column name in your database

//     @ManyToMany(mappedBy = "permissions")
//     private Set<Role> roles;

//     // Constructors, getters, and setters

//     // Default constructor
//     public Permission() {
//     }

//     // Constructor with parameters
//     public Permission(String permissionName) {
//         PermissionName = permissionName;
//     }

//     public Long getPermissionID() {
//         return PermissionID;
//     }

//     public void setPermissionID(Long permissionID) {
//         PermissionID = permissionID;
//     }

//     public String getPermissionName() {
//         return PermissionName;
//     }

//     public void setPermissionName(String permissionName) {
//         PermissionName = permissionName;
//     }

//     public Set<Role> getRoles() {
//         return roles;
//     }

//     public void setRoles(Set<Role> roles) {
//         this.roles = roles;
//     }
// }
