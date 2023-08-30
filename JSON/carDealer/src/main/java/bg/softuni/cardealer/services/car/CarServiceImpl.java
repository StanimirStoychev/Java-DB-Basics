package bg.softuni.cardealer.services.car;

import bg.softuni.cardealer.domain.dtos.car.CarDTO;
import bg.softuni.cardealer.domain.dtos.car.CarInfoWithoutPartsDTO;
import bg.softuni.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.cardealer.constants.Paths.CARS_AND_PARTS_OUTPUT;
import static bg.softuni.cardealer.constants.Paths.TOYOTA_CARS_OUTPUT;
import static bg.softuni.cardealer.constants.Utils.writeIntoJsonFile;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarInfoWithoutPartsDTO> getAllToyotaCarsOrdered() throws IOException {
        List<CarInfoWithoutPartsDTO> toyotaCars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota")
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> modelMapper.map(car, CarInfoWithoutPartsDTO.class))
                .toList();

        writeIntoJsonFile(toyotaCars, TOYOTA_CARS_OUTPUT);

        return toyotaCars;
    }

    @Override
    public List<CarDTO> getAllCarsWithParts() throws IOException {
        List<CarDTO> cars = this.carRepository.findAllByIdGreaterThan(0L)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();

        writeIntoJsonFile(cars, CARS_AND_PARTS_OUTPUT);

        return cars;
    }
}
