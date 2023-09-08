package bg.softuni.cardealer.domain.dtos.car.wrappers;

import bg.softuni.cardealer.domain.dtos.car.CarInfoWithoutPartsDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWrapperDTO {

    @XmlElement(name = "car")
    private List<CarInfoWithoutPartsDTO> cars;
}
