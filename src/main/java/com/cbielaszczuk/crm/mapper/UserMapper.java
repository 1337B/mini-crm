package com.cbielaszczuk.crm.mapper;

import com.cbielaszczuk.crm.dto.UserDTO;
import com.cbielaszczuk.crm.model.UserModel;

/**
 * Utility class to map between UserDTO and UserModel.
 */
public class UserMapper {

    /**
     * Converts a UserDTO to a UserModel.
     *
     * @param dto the user DTO
     * @return the corresponding UserModel
     */
    public static UserModel toModel(UserDTO dto) {
        if (dto == null) return null;
        return new UserModel(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getUsername(),
                dto.getPassword()
        );
    }

    /**
     * Converts a UserModel to a UserDTO.
     *
     * @param model the user model
     * @return the corresponding UserDTO
     */
    public static UserDTO toDTO(UserModel model) {
        if (model == null) return null;
        return new UserDTO(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getPhone(),
                model.getUsername(),
                model.getPassword()
        );
    }
}
