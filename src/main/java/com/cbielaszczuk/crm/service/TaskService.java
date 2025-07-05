package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.dto.TaskDTO;
import com.cbielaszczuk.crm.mapper.TaskMapper;
import com.cbielaszczuk.crm.model.TaskModel;
import com.cbielaszczuk.crm.repository.TaskRepository;
import com.cbielaszczuk.crm.validation.TaskValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles business logic for managing tasks.
 */
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new task from a DTO after validation.
     *
     * @param dto task data
     */
    public void createTask(TaskDTO dto) {
        TaskValidator.validateForCreate(dto);
        TaskModel model = TaskMapper.toModel(dto);
        repository.save(model);
    }

    /**
     * Updates an existing task from a DTO after validation.
     *
     * @param dto updated task data
     */
    public void updateTask(TaskDTO dto) {
        TaskValidator.validateForUpdate(dto);
        TaskModel model = TaskMapper.toModel(dto);
        repository.update(model);
    }

    /**
     * Retrieves all non-deleted tasks.
     *
     * @return list of task DTOs
     */
    public List<TaskDTO> getAllTasks() {
        return repository.getAll().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds a task by its ID.
     *
     * @param id the task ID
     * @return task DTO if found, otherwise null
     */
    public TaskDTO getTaskById(int id) {
        TaskModel model = repository.getById(id);
        return model != null ? TaskMapper.toDTO(model) : null;
    }

    /**
     * Soft deletes a task by setting deletedAt.
     *
     * @param id ID of the task
     */
    public void deleteTask(int id) {
        TaskValidator.validateForDelete(id);
        repository.delete(id);
    }
}
