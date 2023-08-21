package bg.softuni.employee.repositories;

import bg.softuni.employee.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<List<Employee>> findAllByBirthDayBeforeOrderBySalaryDesc(LocalDate year);
}
