package UniversitySystem.Entities;

import Sales.Entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Student extends BaseEntity {

    @Column(length = 30, nullable = false,name = "first_name")
    private String firstName;

    @Column(length = 30, nullable = false, name = "last_name")
    private String lastName;

    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(name = "average_grade")
    private Double averageGrade;

    @Column
    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;
}
