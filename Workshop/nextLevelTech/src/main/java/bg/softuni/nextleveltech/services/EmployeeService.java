package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.models.dtos.employee.EmployeesWrapperDTO;
import bg.softuni.nextleveltech.models.entities.Company;
import bg.softuni.nextleveltech.models.entities.Employee;
import bg.softuni.nextleveltech.models.entities.Project;
import bg.softuni.nextleveltech.repositories.CompanyRepository;
import bg.softuni.nextleveltech.repositories.EmployeeRepository;
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

import static bg.softuni.nextleveltech.constants.Paths.EMPLOYEES_PATH;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtils validator;

    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository,
                           CompanyRepository companyRepository, ModelMapper modelMapper, ValidationUtils validator) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(EMPLOYEES_PATH);
    }

    public void importEmployees() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(EmployeesWrapperDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        EmployeesWrapperDTO employeesWrapperDTOs =
                (EmployeesWrapperDTO) unmarshaller.unmarshal(new FileReader(EMPLOYEES_PATH.toFile()));

        employeesWrapperDTOs.getEmployees()
                .stream()
                .filter(validator::isValid)
                .map(employeeImportDTO -> {
                    Employee employee = this.modelMapper.map(employeeImportDTO, Employee.class);
                    Project project = this.projectRepository.findByName(employee.getProject().getName()).get();
                    Company company = this.companyRepository
                            .findByName(employee.getProject().getCompany().getName()).get();
                    employee.setProject(project);
                    employee.getProject().setCompany(company);
                    return employee;
                }).forEach(this.employeeRepository::saveAndFlush);
    }
}
