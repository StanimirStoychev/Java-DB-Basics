package bg.softuni.cardealer.services.car;

import bg.softuni.cardealer.domain.dtos.car.CarInfoWithoutPartsDTO;

import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarInfoWithoutPartsDTO> getAllToyotaCarsOrdered() throws IOException;
}
