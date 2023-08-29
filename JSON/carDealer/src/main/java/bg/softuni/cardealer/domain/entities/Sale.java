package bg.softuni.cardealer.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @OneToOne(targetEntity = Car.class)
    @Fetch(FetchMode.JOIN)
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    @Fetch(FetchMode.JOIN)
    private Customer customer;

    @Column
    private Integer discount;
}
