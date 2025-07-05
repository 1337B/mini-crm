package com.cbielaszczuk.crm.model;

import java.time.LocalDate;

public class TaskModel {

    private Integer id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private int projectId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate deletedAt;

    /**
     * Constructor usado para crear una nueva tarea desde cero.
     */
    public TaskModel(Integer id, String title, String description, TaskStatusEnum status,
                     int projectId, LocalDate startDate, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.deletedAt = null;
    }

    /**
     * Constructor usado al mapear tareas desde la base de datos (incluye deletedAt).
     */
    public TaskModel(Integer id, String title, String description, TaskStatusEnum status,
                     int projectId, LocalDate startDate, LocalDate dueDate, LocalDate deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.deletedAt = deletedAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }
}
