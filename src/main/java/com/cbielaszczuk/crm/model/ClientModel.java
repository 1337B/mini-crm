package com.cbielaszczuk.crm.model;
import java.time.LocalDateTime;

public class ClientModel extends PersonModel {

    private String company;
    private String notes;
    private LocalDateTime deletedAt;

    /**
     * Constructs a new ClientModel with all relevant fields.
     *
     * @param id       unique identifier of the client
     * @param name     full name of the client
     * @param email    client's email address
     * @param phone    client's phone number
     * @param company  company the client belongs to
     * @param notes    additional notes about the client
     * @param deletedAt timestamp when the client was marked as deleted, if applicable
     */
    public ClientModel(int id, String name, String email, String phone, String company, String notes) {
        super(id, name, email, phone);
        this.company = company;
        this.notes = notes;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
