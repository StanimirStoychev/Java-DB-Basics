package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }
}
