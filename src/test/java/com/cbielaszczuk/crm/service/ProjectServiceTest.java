package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.config.DatabaseInitializer;
import com.cbielaszczuk.crm.dto.ProjectDTO;
import com.cbielaszczuk.crm.model.ProjectStatusEnum;
import com.cbielaszczuk.crm.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    private ProjectService projectService;
    private int createdId;

    @BeforeEach
    void setUp() {
        // Reset the database before each test
        DatabaseInitializer.initialize();

        // Initialize repository and service
        Connection connection = DatabaseConnection.getInstance();
        ProjectRepository repository = new ProjectRepository(connection);
        projectService = new ProjectService(repository);

        // Create a new test project
        ProjectDTO testProject = new ProjectDTO(
                0, "Test Project", "Initial Description",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                ProjectStatusEnum.IN_PROGRESS,
                1 // assuming client with ID 1 exists
        );
        projectService.createProject(testProject);

        // Get the ID of the last created project
        List<ProjectDTO> all = projectService.getAllProjects();
        createdId = all.get(all.size() - 1).getId();
    }

    @Test
    void getAllProjects_shouldReturnAtLeastOne() {
        List<ProjectDTO> all = projectService.getAllProjects();
        assertTrue(all.size() >= 1);
    }

    @Test
    void getProjectById_existing_shouldReturn() {
        ProjectDTO project = projectService.getProjectById(createdId);
        assertNotNull(project);
        assertEquals("Test Project", project.getTitle());
    }

    @Test
    void updateProject_shouldChangeData() {
        ProjectDTO updated = new ProjectDTO(
                createdId, "Updated Title", "Updated Desc",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31),
                ProjectStatusEnum.ON_HOLD,
                1
        );

        projectService.updateProject(updated);
        ProjectDTO after = projectService.getProjectById(createdId);

        assertEquals("Updated Title", after.getTitle());
        assertEquals("Updated Desc", after.getDescription());
        assertEquals(ProjectStatusEnum.ON_HOLD, after.getStatus());
    }

    @Test
    void deleteProject_shouldSoftDelete() {
        projectService.deleteProject(createdId);
        List<ProjectDTO> remaining = projectService.getAllProjects();
        boolean stillExists = remaining.stream().anyMatch(p -> p.getId() == createdId);
        assertFalse(stillExists);
    }
}
