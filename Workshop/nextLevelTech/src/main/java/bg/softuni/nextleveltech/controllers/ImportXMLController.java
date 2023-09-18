package bg.softuni.nextleveltech.controllers;

import bg.softuni.nextleveltech.services.CompanyService;
import bg.softuni.nextleveltech.services.EmployeeService;
import bg.softuni.nextleveltech.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ImportXMLController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ImportXMLController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {

        boolean companiesImported = this.companyService.areImported();
        boolean projectsImported = this.projectService.areImported();
        boolean employeesImported = this.employeeService.areImported();

        boolean[] areImported = {companiesImported, projectsImported, employeesImported};

        model.addAttribute("areImported", areImported);

        return "xml/import-xml";
    }

    @GetMapping("import/companies")
    public String viewCompanies(Model model) throws IOException {
        String xmlContent = this.companyService.getXMLContent();

        model.addAttribute("companies", xmlContent);

        return "xml/import-companies";
    }

    @PostMapping("import/companies")
    public String importCompanies() throws JAXBException, FileNotFoundException {

        this.companyService.importCompanies();

        return "redirect:/import/xml";
    }

    @GetMapping("import/projects")
    public String viewProjects(Model model) throws IOException {
        String xmlContent = this.projectService.getXMLContent();

        model.addAttribute("projects", xmlContent);

        return "xml/import-projects";
    }

    @PostMapping("import/projects")
    public String importProjects() throws JAXBException, FileNotFoundException {
        this.projectService.importProjects();

        return "redirect:/import/xml";
    }

    @GetMapping("import/employees")
    public String viewEmployees(Model model) throws IOException {
        String xmlContent = this.employeeService.getXMLContent();

        model.addAttribute("employees", xmlContent);

        return "xml/import-employees";
    }

    @PostMapping("import/employees")
    public String importEmployees() throws JAXBException, FileNotFoundException {
        this.employeeService.importEmployees();

        return "redirect:/import/xml";
    }
}
