package bg.softuni.employee.services;

import bg.softuni.employee.domain.entities.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    List<Employee> findAllByBirthDayBeforeOrderBySalaryDesc();
}
