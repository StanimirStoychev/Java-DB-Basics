package bg.softuni.cardealer.services.car;

import bg.softuni.cardealer.domain.dtos.car.CarDTO;
import bg.softuni.cardealer.domain.dtos.car.CarInfoWithoutPartsDTO;
import bg.softuni.cardealer.domain.dtos.car.wrappers.CarsExportWrapperDTO;
import bg.softuni.cardealer.domain.dtos.car.wrappers.CarsWrapperDTO;
import bg.softuni.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.cardealer.constants.Paths.*;
import static bg.softuni.cardealer.constants.Utils.writeIntoJsonFile;

@Service
public class CarServiceImpl implements CarService {

    private static final String TOYOTA = "Toyota";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarInfoWithoutPartsDTO> getAllToyotaCarsOrdered() throws IOException, JAXBException {
        List<CarInfoWithoutPartsDTO> toyotaCars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(TOYOTA)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> modelMapper.map(car, CarInfoWithoutPartsDTO.class))
                .toList();

        writeIntoJsonFile(toyotaCars, TOYOTA_CARS_OUTPUT);

        CarsWrapperDTO carsWrapperDTO = new CarsWrapperDTO(toyotaCars);

        JAXBContext context = JAXBContext.newInstance(CarsWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        FileWriter writer = new FileWriter(TOYOTA_CARS_XML_OUTPUT.toFile());

        marshaller.marshal(carsWrapperDTO, writer);

        return toyotaCars;
    }

    @Override
    public List<CarDTO> getAllCarsWithParts() throws IOException, JAXBException {
        List<CarDTO> cars = this.carRepository.findAllByIdGreaterThan(0L)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();

        writeIntoJsonFile(cars, CARS_AND_PARTS_OUTPUT);

        CarsExportWrapperDTO carExportWrapperDTO = new CarsExportWrapperDTO(cars);

        JAXBContext context = JAXBContext.newInstance(CarsExportWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        FileWriter writer = new FileWriter(CARS_AND_PARTS_XML_OUTPUT.toFile());

        marshaller.marshal(carExportWrapperDTO, writer);

        return cars;
    }
}
