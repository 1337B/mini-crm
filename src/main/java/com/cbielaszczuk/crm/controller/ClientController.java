package com.cbielaszczuk.crm.controller;

import com.cbielaszczuk.crm.dto.ClientDTO;
import com.cbielaszczuk.crm.service.ClientService;

import java.util.List;

/**
 * Controller that exposes client-related operations to UI or test layer.
 */
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    public void createClient(ClientDTO dto) {
        service.createClient(dto);
    }

    public List<ClientDTO> getAllClients() {
        return service.getAllClients();
    }

    public ClientDTO getClientById(int id) {
        return service.getClientById(id);
    }

    public void updateClient(ClientDTO dto) {
        service.updateClient(dto);
    }

    public void deleteClient(int id) {
        service.deleteClient(id);
    }
}
