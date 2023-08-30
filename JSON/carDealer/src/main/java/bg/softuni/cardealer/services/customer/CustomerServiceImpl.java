package bg.softuni.cardealer.services.customer;

import bg.softuni.cardealer.domain.dtos.customer.CustomerFullInfoDTO;
import bg.softuni.cardealer.domain.entities.Customer;
import bg.softuni.cardealer.repositories.CustomerRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.cardealer.constants.Paths.ORDERED_CUSTOMERS_OUTPUT;
import static bg.softuni.cardealer.constants.Utils.writeIntoJsonFile;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public List<CustomerFullInfoDTO> getOrderedCustomers() throws IOException {
        List<CustomerFullInfoDTO> customers = customerRepository.getOrderedCustomers()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerFullInfoDTO.class))
                .toList();

        writeIntoJsonFile(customers, ORDERED_CUSTOMERS_OUTPUT);

        return customers;
    }
}
