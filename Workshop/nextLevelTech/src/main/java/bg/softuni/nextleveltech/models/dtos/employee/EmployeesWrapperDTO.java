package bg.softuni.nextleveltech.models.dtos.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesWrapperDTO {

    @XmlElement(name = "employee")
    private List<EmployeeImportDTO> employees;

    public EmployeesWrapperDTO() {}

    public List<EmployeeImportDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeImportDTO> employees) {
        this.employees = employees;
    }
}
