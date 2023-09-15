package bg.softuni.nextleveltech.models.dtos.project;

import bg.softuni.nextleveltech.models.dtos.company.CompanyImportDTO;
import bg.softuni.nextleveltech.utils.adapters.LocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectImportDTO {

    @XmlElement
    @NotNull
    private String name;

    @XmlElement
    @NotNull
    private String description;

    @XmlElement(name = "start-date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @XmlElement(name = "is-finished")
    private boolean isFinished;

    @XmlElement
    @NotNull
    private BigDecimal payment;

    @XmlElement(name = "company")
    @NotNull
    private CompanyImportDTO company;

    public ProjectImportDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public CompanyImportDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyImportDTO company) {
        this.company = company;
    }
}
