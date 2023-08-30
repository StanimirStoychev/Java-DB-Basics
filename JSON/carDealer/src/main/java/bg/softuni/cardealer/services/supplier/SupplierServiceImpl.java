package bg.softuni.cardealer.services.supplier;

import bg.softuni.cardealer.domain.dtos.supplier.SupplierWithPartsDTO;
import bg.softuni.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.cardealer.constants.Paths.LOCAL_SUPPLIERS_OUTPUT;
import static bg.softuni.cardealer.constants.Utils.writeIntoJsonFile;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SupplierWithPartsDTO> getAllSuppliersWhoNotImport() throws IOException {

        List<SupplierWithPartsDTO> suppliers = this.supplierRepository.findAllByIsImporterFalse()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(supplier -> modelMapper.map(supplier, SupplierWithPartsDTO.class))
                .toList();

        writeIntoJsonFile(suppliers, LOCAL_SUPPLIERS_OUTPUT);

        return suppliers;
    }
}
