package bg.softuni.nextleveltech.controllers;

import bg.softuni.nextleveltech.services.EmployeeService;
import bg.softuni.nextleveltech.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("export/project-if-finished")
    public String exportProjects(Model model) {
        String finishedProjects = this.projectService.exportFinishedProjects();

        model.addAttribute("projectsIfFinished", finishedProjects);

        return "export/export-project-if-finished";
    }

    @GetMapping("export/employees-above")
    public String exportEmployees(Model model) {
        String employeesOverAge25 = this.employeeService.exportEmployeesWithAgeAbove25();

        model.addAttribute("employeesAbove", employeesOverAge25);

        return "export/export-employees-with-age";
    }
}
