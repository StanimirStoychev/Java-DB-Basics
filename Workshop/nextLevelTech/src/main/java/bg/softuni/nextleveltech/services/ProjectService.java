package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.models.dtos.project.ProjectsWrapperDTO;
import bg.softuni.nextleveltech.models.entities.Company;
import bg.softuni.nextleveltech.models.entities.Project;
import bg.softuni.nextleveltech.repositories.CompanyRepository;
import bg.softuni.nextleveltech.repositories.ProjectRepository;
import bg.softuni.nextleveltech.utils.validator.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static bg.softuni.nextleveltech.constants.Paths.PROJECTS_PATH;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validator;

    public ProjectService(ProjectRepository projectRepository, CompanyRepository companyRepository, ModelMapper modelMapper, ValidationUtils validator) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(PROJECTS_PATH);
    }

    public void importProjects() throws JAXBException, FileNotFoundException {

        JAXBContext context = JAXBContext.newInstance(ProjectsWrapperDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ProjectsWrapperDTO projectsWrapperDTO =
                (ProjectsWrapperDTO) unmarshaller.unmarshal(new FileReader(PROJECTS_PATH.toFile()));

        projectsWrapperDTO.getProjects()
                .stream()
                .filter(validator::isValid)
                .map(projectImportDTO -> {
                    Project project = this.modelMapper.map(projectImportDTO, Project.class);
                    Company company = this.companyRepository.findByName(projectImportDTO.getCompany().getName()).get();
                    project.setCompany(company);
                    return project;
                }).forEach(this.projectRepository::saveAndFlush);
    }

    public String exportFinishedProjects() {
        return this.projectRepository.findByIsFinishedTrue().get()
                .stream()
                .map(Project::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
