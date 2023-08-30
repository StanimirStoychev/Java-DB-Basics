package bg.softuni.cardealer.services.customer;

import bg.softuni.cardealer.domain.dtos.customer.CustomerFullInfoDTO;
import bg.softuni.cardealer.domain.entities.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    List<CustomerFullInfoDTO> getOrderedCustomers() throws IOException;
}
