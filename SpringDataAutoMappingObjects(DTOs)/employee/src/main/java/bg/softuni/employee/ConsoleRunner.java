package bg.softuni.employee;

import bg.softuni.employee.domain.dtos.EmployeeDTO;
import bg.softuni.employee.domain.entities.Employee;
import bg.softuni.employee.domain.entities.Address;
import bg.softuni.employee.services.AddressService;
import bg.softuni.employee.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;

    public ConsoleRunner(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

//        Address address = new Address("Bulgaria", "Gabrovo");
       // this.addressService.addAddress(address);

//        Employee employee = new Employee("Stanimir", "Stoychev",
//                LocalDate.of(1993, 3, 12), new BigDecimal(2000), address);

//        Employee employee2 = new Employee("Ivan", "Ivanov",
//                LocalDate.of(1986, 12, 3), new BigDecimal(1500), address);

//        Employee employee3 = new Employee("Georgi", "Georgiev",
//                LocalDate.of(1989, 12, 3), new BigDecimal(1000), address);

//        List<Employee> employees = new ArrayList<>();
//        employees.add(employee2);
//        employees.add(employee3);

//        employee.setEmployees(employees);
//        employee.getEmployees().forEach(empl -> empl.setManager(employee));

//        employeeService.addEmployee(employee);
//        employeeService.addEmployee(employee2);
//        employeeService.addEmployee(employee3);

//        ManagerDTO managerDTO = modelMapper.map(employee, ManagerDTO.class);

//        System.out.println(managerDTO);


//        1.)
//        EmployeeDTO1 employeeDTO = modelMapper.map(employee, EmployeeDTO1.class);

//        System.out.println(employeeDTO);

        List<Employee> employees = employeeService.findAllByBirthDayBeforeOrderBySalaryDesc();
        for (Employee employee : employees) {
            EmployeeDTO currentEmployee = modelMapper.map(employee, EmployeeDTO.class);
            System.out.println(currentEmployee);
        }

    }
}
