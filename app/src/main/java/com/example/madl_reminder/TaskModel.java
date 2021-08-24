package com.example.madl_reminder;

public class TaskModel {

    private int id;
    private String task_name;
    private int task_priority;

    public TaskModel(int id, String task_name, int task_priority) {
        this.id = id;
        this.task_name = task_name;
        this.task_priority = task_priority;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", task_name='" + task_name + '\'' +
                ", task_priority=" + task_priority +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getTask_priority() {
        return task_priority;
    }

    public void setTask_priority(int task_priority) {
        this.task_priority = task_priority;
    }
}
