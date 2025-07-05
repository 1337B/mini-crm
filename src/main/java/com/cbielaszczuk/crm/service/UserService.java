package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.dto.UserDTO;
import com.cbielaszczuk.crm.dto.UserLoginDTO;
import com.cbielaszczuk.crm.dto.UserRegistrationDTO;
import com.cbielaszczuk.crm.mapper.UserMapper;
import com.cbielaszczuk.crm.model.UserModel;
import com.cbielaszczuk.crm.repository.UserRepository;
import com.cbielaszczuk.crm.validation.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Registers a new user after validating the data.
     */
    public void register(UserRegistrationDTO registrationDTO) {
        UserValidator.validateRegistration(registrationDTO);
        UserDTO dto = new UserDTO(0,
                registrationDTO.getName(),
                registrationDTO.getEmail(),
                registrationDTO.getPhone(),
                registrationDTO.getUsername(),
                registrationDTO.getPassword()
        );
        UserModel model = UserMapper.toModel(dto);
        repository.save(model);
    }

    /**
     * Authenticates a user by username and password.
     */
    public UserDTO login(UserLoginDTO loginDTO) {
        UserValidator.validateLogin(loginDTO);
        UserModel model = repository.findByUsername(loginDTO.getUsername());
        if (model == null || !model.getPassword().equals(loginDTO.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        return UserMapper.toDTO(model);
    }

    /**
     * Returns all users as DTOs.
     */
    public List<UserDTO> getAllUsers() {
        return repository.getAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Returns a single user by ID.
     */
    public UserDTO getUserById(int id) {
        UserModel model = repository.getById(id);
        return model != null ? UserMapper.toDTO(model) : null;
    }

    /**
     * Updates user data.
     */
    public void updateUser(UserDTO dto) {
        UserValidator.validateForCreateOrUpdate(dto);
        UserModel model = UserMapper.toModel(dto);
        repository.update(model);
    }

    /**
     * Soft-deletes a user.
     */
    public void deleteUser(int id) {
        UserValidator.validateForDelete(id);
        repository.delete(id);
    }
}
