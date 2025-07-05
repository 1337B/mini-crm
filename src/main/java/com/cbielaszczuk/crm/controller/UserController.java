package com.cbielaszczuk.crm.controller;

import com.cbielaszczuk.crm.dto.UserDTO;
import com.cbielaszczuk.crm.dto.UserLoginDTO;
import com.cbielaszczuk.crm.dto.UserRegistrationDTO;
import com.cbielaszczuk.crm.service.UserService;

import java.util.List;

public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Handles user registration.
     */
    public void register(UserRegistrationDTO dto) {
        service.register(dto);
    }

    /**
     * Handles user login.
     */
    public UserDTO login(UserLoginDTO dto) {
        return service.login(dto);
    }

    /**
     * Retrieves all users.
     */
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    /**
     * Retrieves a user by ID.
     */
    public UserDTO getUserById(int id) {
        return service.getUserById(id);
    }

    /**
     * Updates user information.
     */
    public void updateUser(UserDTO dto) {
        service.updateUser(dto);
    }

    /**
     * Deletes (soft) a user by ID.
     */
    public void deleteUser(int id) {
        service.deleteUser(id);
    }
}
