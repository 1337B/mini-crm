package com.cbielaszczuk.crm.service;

import com.cbielaszczuk.crm.dto.ProjectDTO;
import com.cbielaszczuk.crm.mapper.ProjectMapper;
import com.cbielaszczuk.crm.model.ProjectModel;
import com.cbielaszczuk.crm.repository.ProjectRepository;
import com.cbielaszczuk.crm.validation.ProjectValidator;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public void createProject(ProjectDTO dto) {
        ProjectValidator.validateForCreate(dto);
        ProjectModel model = ProjectMapper.toModel(dto);
        repository.save(model);
    }

    public List<ProjectDTO> getAllProjects() {
        return repository.getAll().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(int id) {
        ProjectModel model = repository.getById(id);
        return model != null ? ProjectMapper.toDTO(model) : null;
    }

    public void updateProject(ProjectDTO dto) {
        ProjectValidator.validateForUpdate(dto);
        ProjectModel model = ProjectMapper.toModel(dto);
        repository.update(model);
    }

    public void deleteProject(int id) {
        ProjectValidator.validateForDelete(id);
        repository.delete(id);
    }
}
