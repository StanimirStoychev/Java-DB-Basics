package bg.softuni.employee.domain.dtos;

import bg.softuni.employee.domain.entities.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO2 {

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    public EmployeeDTO2(String stanimir, String stoychev, LocalDate of, BigDecimal bigDecimal, Address address) {}

    public EmployeeDTO2(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
