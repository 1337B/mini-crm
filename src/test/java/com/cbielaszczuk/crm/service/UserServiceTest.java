package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.config.TestDatabaseInitializer;
import com.cbielaszczuk.crm.dto.UserDTO;
import com.cbielaszczuk.crm.dto.UserLoginDTO;
import com.cbielaszczuk.crm.dto.UserRegistrationDTO;
import com.cbielaszczuk.crm.repository.UserRepository;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static UserService service;

    @BeforeAll
    static void setup() {
        TestDatabaseInitializer.initialize();
        Connection conn = DatabaseConnection.getTestInstance();
        service = new UserService(new UserRepository(conn));
    }

    @Test
    void register_shouldPersistUser() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "Juan Tester", "juan@test.com", "999", "juantest", "secret", "secret"
        );

        assertDoesNotThrow(() -> service.register(dto));

        List<UserDTO> all = service.getAllUsers();
        boolean exists = all.stream().anyMatch(u -> u.getUsername().equals("juantest"));
        assertTrue(exists);
    }

    @Test
    void login_shouldReturnUser() {
        UserLoginDTO login = new UserLoginDTO("admin", "admin123");
        UserDTO user = service.login(login);

        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    void updateUser_shouldChangeData() {
        List<UserDTO> users = service.getAllUsers();
        UserDTO user = users.get(0);

        user.setName("New Name");
        user.setEmail("new@email.com");
        service.updateUser(user);

        UserDTO updated = service.getUserById(user.getId());
        assertEquals("New Name", updated.getName());
        assertEquals("new@email.com", updated.getEmail());
    }

    @Test
    void deleteUser_shouldRemoveFromList() {
        UserRegistrationDTO dto = new UserRegistrationDTO(
                "ToDelete", "del@test.com", "777", "todelete", "pass", "pass"
        );
        service.register(dto);

        UserDTO toDelete = service.getAllUsers().stream()
                .filter(u -> u.getUsername().equals("todelete"))
                .findFirst().orElseThrow();

        service.deleteUser(toDelete.getId());

        List<UserDTO> all = service.getAllUsers();
        assertFalse(all.stream().anyMatch(u -> u.getId() == toDelete.getId()));
    }
}
