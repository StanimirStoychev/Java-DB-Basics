package bg.softuni.cardealer.services.seed;

import bg.softuni.cardealer.domain.dtos.car.CarImportDTO;
import bg.softuni.cardealer.domain.dtos.customer.CustomerDTO;
import bg.softuni.cardealer.domain.dtos.part.PartImportDTO;
import bg.softuni.cardealer.domain.dtos.supplier.SupplierDTO;
import bg.softuni.cardealer.domain.entities.Car;
import bg.softuni.cardealer.domain.entities.Customer;
import bg.softuni.cardealer.domain.entities.Part;
import bg.softuni.cardealer.domain.entities.Supplier;
import bg.softuni.cardealer.repositories.CarRepository;
import bg.softuni.cardealer.repositories.CustomerRepository;
import bg.softuni.cardealer.repositories.PartRepository;
import bg.softuni.cardealer.repositories.SupplierRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static bg.softuni.cardealer.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException {
        if (this.supplierRepository.count() == 0) {
            FileReader reader = new FileReader(SUPPLIERS_INPUT_PATH.toFile());

            List<Supplier> suppliers = Arrays.stream(gson.fromJson(reader, SupplierDTO[].class))
                    .map(supplierDTO -> modelMapper.map(supplierDTO, Supplier.class)).toList();

            this.supplierRepository.saveAllAndFlush(suppliers);
        }
    }

    @Override
    public void seedParts() throws FileNotFoundException {
        if (this.partRepository.count() == 0) {
            FileReader reader = new FileReader(PARTS_INPUT_PATH.toFile());

            List<Part> parts = Arrays.stream(gson.fromJson(reader, PartImportDTO[].class))
                    .map(partImportDTO -> modelMapper.map(partImportDTO, Part.class))
                    .toList();
            parts.forEach(part -> part.setSupplier(this.supplierRepository.getRandomEntity()));

            this.partRepository.saveAllAndFlush(parts);
        }
    }

    @Override
    public void seedCars() throws FileNotFoundException {
        if (this.carRepository.count() == 0) {
            FileReader reader = new FileReader(CAR_INPUT_PATH.toFile());

            List<Car> cars = Arrays.stream(gson.fromJson(reader, CarImportDTO[].class))
                    .map(carImportDTO -> modelMapper.map(carImportDTO, Car.class))
                    .map(this::setRandomParts)
                    .toList();

            this.carRepository.saveAllAndFlush(cars);
        }
    }

    private Car setRandomParts(Car car) {
        final Random random = new Random();

        int countOfParts = random.nextInt(3, 6);

        Set<Part> parts = new HashSet<>();

        for (int i = 1; i <= countOfParts; i++) {
            Part part = this.partRepository.getRandomEntity();
            if (parts.contains(part)) {
                --i;
            } else {
                parts.add(part);
            }
        }

        car.setParts(parts);

        return car;
    }

    @Override
    public void seedCustomers() throws FileNotFoundException {
        if (this.customerRepository.count() == 0) {
            FileReader reader = new FileReader(CUSTOMER_INPUT_PATH.toFile());

            List<Customer> customers = Arrays.stream(gson.fromJson(reader, CustomerDTO[].class))
                    .map(customerDTO -> modelMapper.map(customerDTO, Customer.class))
                    .toList();

            this.customerRepository.saveAllAndFlush(customers);
        }
    }

    @Override
    public void seedSales() {

    }
}
