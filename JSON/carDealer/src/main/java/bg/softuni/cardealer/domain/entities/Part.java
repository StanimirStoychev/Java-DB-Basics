package bg.softuni.cardealer.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private Long quantity;

    @ManyToOne(targetEntity = Supplier.class)
    @Fetch(FetchMode.JOIN)
    private Supplier supplier;
}
