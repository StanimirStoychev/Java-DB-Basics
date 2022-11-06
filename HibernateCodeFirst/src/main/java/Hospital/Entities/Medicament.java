package Hospital.Entities;

import Sales.Entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Medicament extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<Patient> patients;
}
