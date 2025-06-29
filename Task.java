package com.examplefourthjuly.todolist;


import androidx.navigation.ui.AppBarConfiguration;

import java.io.Serializable;


public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
   String title;
    String priority;  // "High", "Medium", "Low"
    String dueDate;   // e.g., "2025-06-15"
    boolean isCompleted;
    String description;


    public Task(String title, String description,String priority, String dueDate, boolean isCompleted) {
        this.title = title;
        this.description=description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public String gettitle() { return title; }
    public String getPriority() { return priority; }
    public String description(){return description;}
    public String getDueDate() { return dueDate; }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    @Override
    public String toString() {
        return title + " (" + priority + ") - " + dueDate+"\n"+ description;
    }
}