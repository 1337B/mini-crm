package com.cbielaszczuk.crm.dto;

import com.cbielaszczuk.crm.model.TaskStatusEnum;

import java.time.LocalDate;

public class TaskDTO {

    private Integer id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private Integer projectId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate deletedAt;

    /**
     * Constructor for editing or loading a task with full data.
     *
     * @param id          unique ID
     * @param title       task title
     * @param description task details
     * @param status      current status (enum)
     * @param projectId   the project it belongs to
     * @param startDate   optional start date
     * @param dueDate     optional due date
     * @param deletedAt   soft delete timestamp
     */

    public TaskDTO(Integer id, String title, String description, TaskStatusEnum status,
                   Integer projectId, LocalDate startDate, LocalDate dueDate, LocalDate deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.deletedAt = deletedAt;
    }

    /**
     * Constructor for creating a task (no ID or deletedAt yet).
     *
     * @param title       task title
     * @param description task details
     * @param status      current status
     * @param projectId   linked project ID
     * @param startDate   optional start date
     * @param dueDate     optional due date
     */
    public TaskDTO(String title, String description, TaskStatusEnum status,
                   Integer projectId, LocalDate startDate, LocalDate dueDate) {
        this(null, title, description, status, projectId, startDate, dueDate, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { // útil para editar
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

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }
}
