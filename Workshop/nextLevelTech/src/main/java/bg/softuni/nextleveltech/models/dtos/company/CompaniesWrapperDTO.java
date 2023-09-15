package bg.softuni.nextleveltech.models.dtos.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompaniesWrapperDTO {

    @XmlElement(name = "company")
    private List<CompanyImportDTO> companies;

    public CompaniesWrapperDTO() {}

    public List<CompanyImportDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyImportDTO> companies) {
        this.companies = companies;
    }
}
