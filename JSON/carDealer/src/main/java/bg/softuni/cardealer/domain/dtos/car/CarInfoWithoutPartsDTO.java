package bg.softuni.cardealer.domain.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoWithoutPartsDTO {

    private Long id;

    private String make;

    private String model;

    private Long travelledDistance;
}
