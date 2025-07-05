package com.cbielaszczuk.crm.controller;

import com.cbielaszczuk.crm.dto.ProjectDTO;
import com.cbielaszczuk.crm.service.ProjectService;

import java.util.List;

public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    public void create(ProjectDTO dto) {
        try {
            service.createProject(dto);
            System.out.println("Project created successfully.");
        } catch (Exception e) {
            System.err.println("Failed to create project: " + e.getMessage());
        }
    }

    public List<ProjectDTO> listAll() {
        return service.getAllProjects();
    }

    public ProjectDTO findById(int id) {
        return service.getProjectById(id);
    }

    public void update(ProjectDTO dto) {
        try {
            service.updateProject(dto);
            System.out.println("Project updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to update project: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            service.deleteProject(id);
            System.out.println("Project deleted (soft delete) successfully.");
        } catch (Exception e) {
            System.err.println("Failed to delete project: " + e.getMessage());
        }
    }
}
