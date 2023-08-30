package bg.softuni.cardealer.domain.dtos.car;

import bg.softuni.cardealer.domain.dtos.part.PartWithNameAndPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CarDTO {

    private String make;

    private String model;

    private Long travelledDistance;

    private Set<PartWithNameAndPriceDTO> parts;

    public CarDTO() {
        this.parts = new HashSet<>();
    }
}
