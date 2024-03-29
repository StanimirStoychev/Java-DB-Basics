package bg.softuni.cardealer.services.seed;

import bg.softuni.cardealer.domain.dtos.car.wrappers.CarsImportWrapperDTO;
import bg.softuni.cardealer.domain.dtos.customer.CustomerImportDTO;
import bg.softuni.cardealer.domain.dtos.customer.wrappers.CustomersImportWrapperDTO;
import bg.softuni.cardealer.domain.dtos.part.wrappers.PartImportWrapperDTO;
import bg.softuni.cardealer.domain.dtos.supplier.wrappers.SuppliersWrapperDTO;
import bg.softuni.cardealer.domain.entities.*;
import bg.softuni.cardealer.repositories.*;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static bg.softuni.cardealer.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository, Gson gson, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;

        Converter<LocalDateTime, String> toDateToString =
                ctx -> DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ctx.getSource());

        modelMapper.createTypeMap(CustomerImportDTO.class, Customer.class)
                .addMapping(CustomerImportDTO::getBirthDate, Customer::setBirthDate);
    }

    @Override
    public void seedSuppliers() throws FileNotFoundException, JAXBException {
        if (this.supplierRepository.count() == 0) {
//            FileReader reader = new FileReader(SUPPLIERS_INPUT_PATH.toFile());
//
//            List<Supplier> suppliers = Arrays.stream(gson.fromJson(reader, SupplierImportDTO[].class))
//                    .map(supplierDTO -> modelMapper.map(supplierDTO, Supplier.class)).toList();

            FileReader reader = new FileReader(SUPPLIERS_INPUT_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(SuppliersWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SuppliersWrapperDTO suppliersWrapperDTO = (SuppliersWrapperDTO) unmarshaller.unmarshal(reader);

            List<Supplier> suppliers = suppliersWrapperDTO.getSuppliers()
                    .stream()
                    .map(supplierImportDTO -> modelMapper.map(supplierImportDTO, Supplier.class))
                    .toList();

            this.supplierRepository.saveAllAndFlush(suppliers);
        }
    }

    @Override
    public void seedParts() throws FileNotFoundException, JAXBException {
        if (this.partRepository.count() == 0) {
//            FileReader reader = new FileReader(PARTS_INPUT_PATH.toFile());
//
//            List<Part> parts = Arrays.stream(gson.fromJson(reader, PartImportDTO[].class))
//                    .map(partImportDTO -> modelMapper.map(partImportDTO, Part.class))
//                    .toList();
//            parts.forEach(part -> part.setSupplier(this.supplierRepository.getRandomEntity()));

            FileReader reader = new FileReader(PARTS_INPUT_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(PartImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            PartImportWrapperDTO partImportWrapperDTO = (PartImportWrapperDTO) unmarshaller.unmarshal(reader);

            List<Part> parts = partImportWrapperDTO.getParts()
                    .stream()
                    .map(partImportDTO -> modelMapper.map(partImportDTO, Part.class))
                    .toList();
            parts.forEach(part -> part.setSupplier(this.supplierRepository.getRandomEntity()));

            this.partRepository.saveAllAndFlush(parts);
        }
    }

    @Override
    public void seedCars() throws FileNotFoundException, JAXBException {
        if (this.carRepository.count() == 0) {
//            FileReader reader = new FileReader(CAR_INPUT_PATH.toFile());
//
//            List<Car> cars = Arrays.stream(gson.fromJson(reader, CarImportDTO[].class))
//                    .map(carImportDTO -> modelMapper.map(carImportDTO, Car.class))
//                    .map(this::setRandomParts)
//                    .toList();

            FileReader reader = new FileReader(CAR_INPUT_XML_PATH.toFile());
            JAXBContext context = JAXBContext.newInstance(CarsImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            CarsImportWrapperDTO carsImportWrapperDTO = (CarsImportWrapperDTO) unmarshaller.unmarshal(reader);

            List<Car> cars = carsImportWrapperDTO.getCars()
                    .stream()
                    .map(carImportDTO -> modelMapper.map(carImportDTO, Car.class))
                    .map(this::setRandomParts)
                    .toList();

            this.carRepository.saveAllAndFlush(cars);
        }
    }

    private Car setRandomParts(Car car) {
        final Random random = new Random();

        int countOfParts = random.nextInt(10, 21);

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
    public void seedCustomers() throws FileNotFoundException, JAXBException {
        if (this.customerRepository.count() == 0) {
//            FileReader reader = new FileReader(CUSTOMER_INPUT_PATH.toFile());
//
//            List<Customer> customers = Arrays.stream(gson.fromJson(reader, CustomerImportDTO[].class))
//                    .map(customerDTO -> modelMapper.map(customerDTO, Customer.class))
//                    .toList();

            FileReader reader = new FileReader(CUSTOMER_INPUT_XML_PATH.toFile());
            JAXBContext context = JAXBContext.newInstance(CustomersImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            CustomersImportWrapperDTO wrapperDTO = (CustomersImportWrapperDTO) unmarshaller.unmarshal(reader);

            List<Customer> customers = wrapperDTO.getCustomers()
                    .stream()
                    .map(customerImportDTO -> modelMapper.map(customerImportDTO, Customer.class))
                    .toList();

            this.customerRepository.saveAllAndFlush(customers);
        }
    }

    @Override
    @Transactional
    public void seedSales() {
        if (this.saleRepository.count() == 0) {

            final Random random = new Random();

            for (int i = 0; i < 30; i++) {
                Car car = this.carRepository.getRandomEntity();
                Customer customer = this.customerRepository.getRandomEntity();
                int discount = random.nextInt(1, 11) * 5;

                Sale sale = new Sale(car, customer, discount);
                
                this.saleRepository.saveAndFlush(sale);
            }
        }
    }
}
