package bg.softuni.employee.domain.dtos;

import java.util.ArrayList;
import java.util.List;

public class ManagerDTO {

    private String firstName;

    private String lastName;

    private List<EmployeeDTO2> employees;

    public ManagerDTO() {
        this.employees = new ArrayList<>();
    }

    public ManagerDTO(String firstName, String lastName, List<EmployeeDTO2> employeeDTOs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employees = employeeDTOs;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDTO2> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO2> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s | Employees:%d", firstName, lastName, employees.size()));

        employees
                .forEach(e -> sb.append(String.format("%n%s %s %.2f", e.getFirstName(),
                        e.getLastName(), e.getSalary())));

        return sb.toString();
    }
}
