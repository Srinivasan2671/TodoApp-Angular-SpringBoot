package com.exam.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @JsonProperty("isDone") // Ensures JSON serialization/deserialization uses "isDone"
    @Column(name = "is_done", nullable = false)// Ensures correct DB column mapping
    private boolean done;
    @JsonProperty("userId")// Ensure JSON serialization
    @Column(name = "user_id", nullable = false) // Link todo to a specific user
    private String userId;
    
    public Todo() {
    }

    public Todo(String title, String description, boolean done, String userId) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
}
