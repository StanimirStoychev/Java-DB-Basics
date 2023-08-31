package bg.softuni.cardealer.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column
    private String name;

    @Column(name = "date_of_birth")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private Boolean isYoungDriver;

}
