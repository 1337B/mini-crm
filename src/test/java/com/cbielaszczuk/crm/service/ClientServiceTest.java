package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.config.TestDatabaseInitializer;
import com.cbielaszczuk.crm.dto.ClientDTO;
import com.cbielaszczuk.crm.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    private ClientService service;
    private int createdId;

    @BeforeEach
    void setUp() {
        // Reset database schema and seed base data before each test
        TestDatabaseInitializer.initialize();

        // Prepare repository and service
        Connection connection = DatabaseConnection.getInstance();
        ClientRepository repository = new ClientRepository(connection);
        service = new ClientService(repository);

        // Create a new client for testing
        ClientDTO testClient = new ClientDTO(0, "Juan Test", "juan@test.com", "1234567890", "TestCorp", "Initial notes");
        service.createClient(testClient);

        // Store ID of the newly created client
        List<ClientDTO> all = service.getAllClients();
        createdId = all.get(all.size() - 1).getId();
    }

    @Test
    void getAllClients_shouldReturnAtLeastOne() {
        List<ClientDTO> all = service.getAllClients();
        assertTrue(all.size() >= 1);
    }

    @Test
    void getClientById_existing_shouldReturn() {
        ClientDTO fetched = service.getClientById(createdId);
        assertNotNull(fetched);
    }

    @Test
    void updateClient_shouldModifyData() {
        ClientDTO updated = new ClientDTO(createdId, "Carlos Bielaszczuk", "carlos@test.com", "0987654321", "TestCorp", "Updated notes");
        service.updateClient(updated);

        ClientDTO after = service.getClientById(createdId);
        assertEquals("Carlos Bielaszczuk", after.getName());
    }

    @Test
    void deleteClient_shouldSoftDelete() {
        service.deleteClient(createdId);
        List<ClientDTO> remaining = service.getAllClients();

        boolean exists = remaining.stream().anyMatch(c -> c.getId() == createdId);
        assertFalse(exists);
    }

    @Test
    void createClient_withInvalidEmail_shouldFail() {
        ClientDTO invalid = new ClientDTO(0, "Fake", "mal@", "123", "C", "Notes");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.createClient(invalid));
        assertTrue(ex.getMessage().toLowerCase().contains("email"));
    }
}
