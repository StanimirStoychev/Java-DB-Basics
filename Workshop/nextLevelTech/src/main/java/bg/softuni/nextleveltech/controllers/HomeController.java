package bg.softuni.nextleveltech.controllers;

import bg.softuni.nextleveltech.services.CompanyService;
import bg.softuni.nextleveltech.services.EmployeeService;
import bg.softuni.nextleveltech.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public HomeController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        boolean areImported = this.companyService.areImported() &&
                this.projectService.areImported() && this.employeeService.areImported();

        model.addAttribute("areImported", areImported);

        return "home";
    }
}
