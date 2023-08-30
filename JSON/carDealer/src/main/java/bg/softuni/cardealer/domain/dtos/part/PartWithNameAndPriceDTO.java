package bg.softuni.cardealer.domain.dtos.part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartWithNameAndPriceDTO {

    private String name;

    private BigDecimal price;
}
