package bg.softuni.employee.services;

import bg.softuni.employee.domain.entities.Address;
import bg.softuni.employee.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddress(Address address) {
            this.addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return this.addressRepository.findById(id).get();
    }
}
