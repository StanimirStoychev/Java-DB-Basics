package bg.softuni.employee.domain.dtos;

import java.math.BigDecimal;

public class EmployeeDTO1 {

    private String firstName;

    private BigDecimal salary;

    private String addressCity;

    public EmployeeDTO1() {
    }

    public EmployeeDTO1(String firstName, BigDecimal salary, String addressCity) {
        this.firstName = firstName;
        this.salary = salary;
        this.addressCity = addressCity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Override
    public String toString() {
        return "EmployeeDTO1{" +
                "firstName='" + firstName + '\'' +
                ", salary=" + salary +
                ", addressCity='" + addressCity + '\'' +
                '}';
    }
}
