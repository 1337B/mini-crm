package com.cbielaszczuk.crm.mapper;

import com.cbielaszczuk.crm.dto.TaskDTO;
import com.cbielaszczuk.crm.model.TaskModel;

public class TaskMapper {

    /**
     * Convierte un TaskDTO a TaskModel.
     *
     * @param dto el DTO
     * @return el modelo correspondiente
     */
    public static TaskModel toModel(TaskDTO dto) {
        if (dto == null) return null;

        if (dto.getDeletedAt() == null) {
            return new TaskModel(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getStatus(),
                    dto.getProjectId(),
                    dto.getStartDate(),
                    dto.getDueDate()
            );
        } else {
            return new TaskModel(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getStatus(),
                    dto.getProjectId(),
                    dto.getStartDate(),
                    dto.getDueDate(),
                    dto.getDeletedAt()
            );
        }
    }

    /**
     * Convierte un TaskModel a TaskDTO.
     *
     * @param model el modelo
     * @return el DTO correspondiente
     */
    public static TaskDTO toDTO(TaskModel model) {
        if (model == null) return null;

        return new TaskDTO(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.getStatus(),
                model.getProjectId(),
                model.getStartDate(),
                model.getDueDate(),
                model.getDeletedAt()
        );
    }
}
