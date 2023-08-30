package bg.softuni.cardealer.domain.dtos.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierWithPartsDTO {

    private Long id;

    private String name;

    private Integer partsCount;
}
