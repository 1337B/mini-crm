package com.cbielaszczuk.crm.mapper;

import com.cbielaszczuk.crm.dto.ClientDTO;
import com.cbielaszczuk.crm.model.ClientModel;

public class ClientMapper {

    /**
     * Converts a ClientDTO to ClientModel.
     *
     * @param dto the DTO object
     * @return the corresponding ClientModel
     */
    public static ClientModel toModel(ClientDTO dto) {
        if (dto == null) return null;

        return new ClientModel(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getCompany(),
                dto.getNotes()
        );
    }

    /**
     * Converts a ClientModel to ClientDTO.
     *
     * @param model the entity object
     * @return the corresponding ClientDTO
     */
    public static ClientDTO toDTO(ClientModel model) {
        if (model == null) return null;

        return new ClientDTO(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getPhone(),
                model.getCompany(),
                model.getNotes()
        );
    }
}
