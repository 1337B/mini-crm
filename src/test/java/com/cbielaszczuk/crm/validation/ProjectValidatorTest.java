package com.cbielaszczuk.crm.validation;

import com.cbielaszczuk.crm.dto.ProjectDTO;
import com.cbielaszczuk.crm.model.ProjectStatusEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectValidatorTest {

    @Test
    void validateForCreate_validData_shouldPass() {
        ProjectDTO dto = new ProjectDTO(1, "Project X", "Description", LocalDate.now(), LocalDate.now().plusDays(5), ProjectStatusEnum.NOT_STARTED, 1);
        assertDoesNotThrow(() -> ProjectValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_nullDto_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> ProjectValidator.validateForCreate(null));
    }

    @Test
    void validateForCreate_blankTitle_shouldThrow() {
        ProjectDTO dto = new ProjectDTO(1, "   ", "desc", null, null, ProjectStatusEnum.NOT_STARTED, 1);
        assertThrows(IllegalArgumentException.class, () -> ProjectValidator.validateForCreate(dto));
    }

    @Test
    void validateForCreate_invalidClientId_shouldThrow() {
        ProjectDTO dto = new ProjectDTO(1, "Project", "desc", null, null, ProjectStatusEnum.NOT_STARTED, 0);
        assertThrows(IllegalArgumentException.class, () -> ProjectValidator.validateForCreate(dto));
    }

    @Test
    void validateForUpdate_valid_shouldPass() {
        ProjectDTO dto = new ProjectDTO(1, "Project", "desc", null, null, ProjectStatusEnum.NOT_STARTED, 1);
        assertDoesNotThrow(() -> ProjectValidator.validateForUpdate(dto));
    }

    @Test
    void validateForDelete_validId_shouldPass() {
        assertDoesNotThrow(() -> ProjectValidator.validateForDelete(1));
    }

    @Test
    void validateForDelete_invalidId_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> ProjectValidator.validateForDelete(0));
    }
}
