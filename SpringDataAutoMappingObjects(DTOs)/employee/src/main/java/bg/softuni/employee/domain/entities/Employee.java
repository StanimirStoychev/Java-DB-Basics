package bg.softuni.employee.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column
    private BigDecimal salary;

    @Column
    boolean holiday;

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "adresses_id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Employee manager;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Employee> employees;

    public Employee() {
        this.employees = new ArrayList<>();
        this.holiday = false;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthDay, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthDay = birthDay;
        this.address = address;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee(String firstName, String lastName, LocalDate birthDay, BigDecimal salary,
                    Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.salary = salary;
        this.address = address;
    }

    public Long getId() {
        return id;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
