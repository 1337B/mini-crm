package com.cbielaszczuk.crm.mapper;

import com.cbielaszczuk.crm.dto.ProjectDTO;
import com.cbielaszczuk.crm.model.ProjectModel;

public class ProjectMapper {

    /**
     * Converts a ProjectDTO to ProjectModel.
     *
     * @param dto the DTO object
     * @return the corresponding ProjectModel
     */
    public static ProjectModel toModel(ProjectDTO dto) {
        if (dto == null) return null;

        ProjectModel model = new ProjectModel(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getClientId()
        );

        model.setStartDate(dto.getStartDate());
        model.setDueDate(dto.getDueDate());
        model.setStatus(dto.getStatus());

        return model;
    }

    /**
     * Converts a ProjectModel to ProjectDTO.
     *
     * @param model the entity object
     * @return the corresponding ProjectDTO
     */
    public static ProjectDTO toDTO(ProjectModel model) {
        if (model == null) return null;

        ProjectDTO dto = new ProjectDTO(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.getStartDate(),
                model.getDueDate(),
                model.getStatus(),
                model.getClientId()
        );

        return dto;
    }
}
