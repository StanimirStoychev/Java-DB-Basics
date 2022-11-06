package Sales.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class StoreLocation extends BaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;
}
