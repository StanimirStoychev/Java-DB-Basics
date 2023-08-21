package bg.softuni.employee.services;

import bg.softuni.employee.domain.entities.Employee;
import bg.softuni.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllByBirthDayBeforeOrderBySalaryDesc() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        return this.employeeRepository.findAllByBirthDayBeforeOrderBySalaryDesc(year1990).get();
    }


}
