package UniversitySystem.Entities;

import Sales.Entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends BaseEntity {

    @Column(length = 30, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 30, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(length = 30, unique = true)
    private String email;

    @Column(name = "salary_per_hour")
    private BigDecimal salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
 }
