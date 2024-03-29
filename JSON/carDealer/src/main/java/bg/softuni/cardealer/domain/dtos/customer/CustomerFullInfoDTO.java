package bg.softuni.cardealer.domain.dtos.customer;

import bg.softuni.cardealer.domain.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFullInfoDTO {

    private Long id;

    private String name;

    private String birthDate;

    private Boolean isYoungDriver;

    private Sale[] sales;
}
