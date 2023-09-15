package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }
}
