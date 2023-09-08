package bg.softuni.cardealer.services.car;

import bg.softuni.cardealer.domain.dtos.car.CarDTO;
import bg.softuni.cardealer.domain.dtos.car.CarInfoWithoutPartsDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {

    List<CarInfoWithoutPartsDTO> getAllToyotaCarsOrdered() throws IOException, JAXBException;

    List<CarDTO> getAllCarsWithParts() throws IOException, JAXBException;
}
