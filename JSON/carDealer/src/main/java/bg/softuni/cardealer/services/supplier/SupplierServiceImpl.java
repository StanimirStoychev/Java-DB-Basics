package bg.softuni.cardealer.services.supplier;

import bg.softuni.cardealer.domain.dtos.supplier.SupplierExportDTO;
import bg.softuni.cardealer.domain.dtos.supplier.SupplierWithPartsDTO;
import bg.softuni.cardealer.domain.dtos.supplier.wrappers.SuppliersExportWrapperDTO;
import bg.softuni.cardealer.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static bg.softuni.cardealer.constants.Paths.LOCAL_SUPPLIERS_OUTPUT;
import static bg.softuni.cardealer.constants.Paths.LOCAL_SUPPLIERS_XML_OUTPUT;
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
    @Transactional
    public List<SupplierWithPartsDTO> getAllSuppliersWhoNotImport() throws IOException, JAXBException {

        List<SupplierWithPartsDTO> suppliers = this.supplierRepository.findAllByIsImporterFalse()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(supplier -> modelMapper.map(supplier, SupplierWithPartsDTO.class))
                .toList();

        writeIntoJsonFile(suppliers, LOCAL_SUPPLIERS_OUTPUT);

        List<SupplierExportDTO> supplierExportDTOS = this.supplierRepository.findAllWhoDontImportParts()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .toList();

        SuppliersExportWrapperDTO suppliersExportWrapperDTO = new SuppliersExportWrapperDTO(supplierExportDTOS);

        JAXBContext context = JAXBContext.newInstance(SuppliersExportWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        FileWriter writer = new FileWriter(LOCAL_SUPPLIERS_XML_OUTPUT.toFile());

        marshaller.marshal(suppliersExportWrapperDTO, writer);

        return suppliers;
    }
}
