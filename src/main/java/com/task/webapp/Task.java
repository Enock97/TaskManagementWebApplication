package com.task.webapp;

import jakarta.persistence.*;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @Column(name = "is_completed")
    private boolean is_completed;

    public Task() {
    }

    //Constructor
    public Task(int id, String name, String description, boolean is_completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.is_completed = is_completed;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return is_completed;
    }

    public void setCompleted(boolean completed) {
        is_completed = completed;
    }
}
