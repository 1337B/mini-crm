package com.cbielaszczuk.crm.controller;

import com.cbielaszczuk.crm.dto.TaskDTO;
import com.cbielaszczuk.crm.service.TaskService;
import com.cbielaszczuk.crm.validation.TaskValidator;

import java.util.List;

public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void create(TaskDTO dto) {
        TaskValidator.validateForCreate(dto);
        service.createTask(dto);
    }

    public List<TaskDTO> getAll() {
        return service.getAllTasks();
    }

    public TaskDTO getById(int id) {
        return service.getTaskById(id);
    }

    public void update(TaskDTO dto) {
        TaskValidator.validateForUpdate(dto);
        service.updateTask(dto);
    }

    public void delete(int id) {
        TaskValidator.validateForDelete(id);
        service.deleteTask(id);
    }
}
