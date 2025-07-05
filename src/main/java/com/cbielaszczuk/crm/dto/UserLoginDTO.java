package com.cbielaszczuk.crm.dto;

/**
 * Data Transfer Object for login requests.
 */
public class UserLoginDTO {

    private String username;
    private String password;

    /**
     * Constructs a UserLoginDTO.
     *
     * @param username login username
     * @param password login password
     */
    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
