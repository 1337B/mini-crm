package com.cbielaszczuk.crm.model;

public class UserModel extends PersonModel {

    private String username;
    private String password;

    /**
     * Constructs a new UserModel with all required fields.
     *
     * @param id        unique identifier of the user
     * @param name      full name of the user
     * @param email     user's email address
     * @param phone     user's phone number
     * @param username  login username
     * @param password  login password
     */
    public UserModel(int id, String name, String email, String phone, String username, String password) {
        super(id, name, email, phone);
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
