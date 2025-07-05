package com.cbielaszczuk.crm.dto;

import com.cbielaszczuk.crm.model.ProjectStatusEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;

public  class ProjectDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private ProjectStatusEnum status;
    private Integer clientId;

    /**
     * Constructor for ProjectDTO.
     * @param id the project ID
     * @param title the  project title
     * @param description the project description
     * @param startDate the project start date
     * @param dueDate the project due date
     * @param status the project status
     * @param clientId the associated client ID
     */

    public ProjectDTO(Integer id, String title, String description, LocalDate startDate, LocalDate dueDate, ProjectStatusEnum status,Integer clientId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
        this.clientId = clientId;
    }

    public Integer getId() {
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public ProjectStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEnum status) {
        this.status = status;
    }
}