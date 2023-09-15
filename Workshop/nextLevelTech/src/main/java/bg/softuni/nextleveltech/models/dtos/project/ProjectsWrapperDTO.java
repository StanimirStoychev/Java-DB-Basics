package bg.softuni.nextleveltech.models.dtos.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectsWrapperDTO {

    @XmlElement(name = "project")
    private List<ProjectImportDTO> projects;

    public ProjectsWrapperDTO() {}

    public List<ProjectImportDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectImportDTO> projects) {
        this.projects = projects;
    }
}
