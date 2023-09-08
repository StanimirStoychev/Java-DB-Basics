package bg.softuni.cardealer.services.customer;

import bg.softuni.cardealer.domain.dtos.customer.CustomerFullInfoDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    List<CustomerFullInfoDTO> writeOrderedCustomers() throws IOException, JAXBException;
}
