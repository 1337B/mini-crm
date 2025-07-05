package com.cbielaszczuk.crm.dto;

/**
 * Data Transfer Object for Client.
 * Used to transfer client data between the UI, service, and persistence layers.
 */
public class ClientDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String notes;

    /**
     * Constructs a new ClientDTO with all relevant fields.
     *
     * @param id      unique identifier of the client
     * @param name    client's full name
     * @param email   client's email address
     * @param phone   client's phone number
     * @param company company the client is associated with
     * @param notes   additional comments or notes
     */
    public ClientDTO(int id, String name, String email, String phone, String company, String notes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
