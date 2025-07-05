package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.config.DatabaseInitializer;
import com.cbielaszczuk.crm.dto.TaskDTO;
import com.cbielaszczuk.crm.model.TaskStatusEnum;
import com.cbielaszczuk.crm.repository.TaskRepository;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {

    private Connection connection;
    private TaskService taskService;
    private int createdId;

    @BeforeAll
    void setupDatabase() {
        DatabaseInitializer.initialize();
        connection = DatabaseConnection.getInstance();
        taskService = new TaskService(new TaskRepository(connection));
        System.out.println("✅ TaskServiceTest initialized.");
    }

    @BeforeEach
    void resetTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM tasks");
        }

        // Insert a test task
        TaskDTO task = new TaskDTO(null, "Tarea 1", "Descripción", TaskStatusEnum.NOT_STARTED, 1,
                LocalDate.now(), LocalDate.now().plusDays(5), null);
        taskService.createTask(task);

        List<TaskDTO> all = taskService.getAllTasks();
        createdId = all.get(all.size() - 1).getId();
    }

    @Test
    void getTaskById_existing_shouldReturn() {
        TaskDTO found = taskService.getTaskById(createdId);
        assertNotNull(found);
        assertEquals("Tarea 1", found.getTitle());
    }

    @Test
    void createTask_shouldPersistData() {
        List<TaskDTO> all = taskService.getAllTasks();
        assertEquals(1, all.size());
    }

    @Test
    void updateTask_shouldChangeData() {
        TaskDTO updated = new TaskDTO(createdId, "Tarea Modificada", "Otra desc", TaskStatusEnum.IN_PROGRESS, 1,
                LocalDate.now(), LocalDate.now().plusDays(7), null);
        taskService.updateTask(updated);

        TaskDTO after = taskService.getTaskById(createdId);
        assertEquals("Tarea Modificada", after.getTitle());
        assertEquals(TaskStatusEnum.IN_PROGRESS, after.getStatus());
    }

    @Test
    void deleteTask_shouldSoftDelete() {
        taskService.deleteTask(createdId);
        List<TaskDTO> remaining = taskService.getAllTasks();

        boolean exists = remaining.stream().anyMatch(t -> t.getId() == createdId);
        assertFalse(exists);
    }
}
