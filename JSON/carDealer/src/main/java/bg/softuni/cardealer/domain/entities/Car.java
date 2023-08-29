package bg.softuni.cardealer.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column
    private String make;

    @Column
    private String model;

    @Column(name = "travelled_distance")
    private Long travelledDistance;

    @ManyToMany(targetEntity = Part.class, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Part> parts;

    public Car() {
        this.parts = new HashSet<>();
    }
}
