package bg.softuni.cardealer.services.supplier;

import bg.softuni.cardealer.domain.dtos.supplier.SupplierWithPartsDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SupplierService {

    List<SupplierWithPartsDTO> getAllSuppliersWhoNotImport() throws IOException, JAXBException;
}
