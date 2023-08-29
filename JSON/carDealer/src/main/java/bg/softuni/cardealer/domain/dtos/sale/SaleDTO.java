package bg.softuni.cardealer.domain.dtos.sale;

import bg.softuni.cardealer.domain.entities.Car;
import bg.softuni.cardealer.domain.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Car car;

    private Customer customer;

    private Integer discount;
}
