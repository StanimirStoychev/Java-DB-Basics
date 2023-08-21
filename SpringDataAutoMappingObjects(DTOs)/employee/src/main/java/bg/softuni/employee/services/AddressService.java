package bg.softuni.employee.services;

import bg.softuni.employee.domain.entities.Address;

public interface AddressService {

    void addAddress(Address address);

    Address findById(Long id);
}
