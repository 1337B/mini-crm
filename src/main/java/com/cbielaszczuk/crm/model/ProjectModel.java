package com.cbielaszczuk.crm.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectModel {

    private Integer id;
    private String title;
    private String description;
    private ProjectStatusEnum status;
    private int clientId;

    private LocalDate startDate;     // Opcional
    private LocalDate dueDate;       // Opcional
    private LocalDateTime deletedAt; // Soft delete

    // ———————————————————————————
    // Constructor principal (solo campos requeridos)
    // ———————————————————————————
    public ProjectModel(Integer id, String title, String description, ProjectStatusEnum status, int clientId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
    }

    // ———————————————————————————
    // Getters & Setters
    // ———————————————————————————

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ProjectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEnum status) {
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}