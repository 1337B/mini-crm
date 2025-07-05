package com.cbielaszczuk.crm.validation;

import com.cbielaszczuk.crm.dto.TaskDTO;

/**
 * Validates task data for creation and update operations.
 */
public class TaskValidator {

    /**
     * Validates required fields for creating a task.
     *
     * @param dto the task data
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateForCreate(TaskDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Task data must not be null.");
        }

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title is required.");
        }

        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new IllegalArgumentException("Task description is required.");
        }

        if (dto.getStatus() == null) {
            throw new IllegalArgumentException("Task status is required.");
        }

        if (dto.getProjectId() == null || dto.getProjectId() <= 0) {
            throw new IllegalArgumentException("Valid project ID is required.");
        }

        if (dto.getDueDate() != null && dto.getStartDate() != null &&
                dto.getDueDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("Due date cannot be before start date.");
        }
    }

    /**
     * Validates required fields for updating a task.
     *
     * @param dto the task data
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateForUpdate(TaskDTO dto) {
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new IllegalArgumentException("Valid task ID is required for update.");
        }

        validateForCreate(dto);
    }

    /**
     * Validates that the task ID is valid for deletion.
     *
     * @param id task ID
     * @throws IllegalArgumentException if ID is invalid
     */
    public static void validateForDelete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Valid task ID is required for deletion.");
        }
    }
}
