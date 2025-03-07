package com.exam.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")// Ensure this matches the database column
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;
    
    public User() {
    }
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
