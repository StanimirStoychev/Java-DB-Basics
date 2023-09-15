package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.models.dtos.company.CompaniesWrapperDTO;
import bg.softuni.nextleveltech.models.entities.Company;
import bg.softuni.nextleveltech.repositories.CompanyRepository;
import bg.softuni.nextleveltech.utils.validator.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import static bg.softuni.nextleveltech.constants.Paths.COMPANIES_PATH;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validator;

    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper, ValidationUtils validator) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(COMPANIES_PATH);
    }

    public void importCompanies() throws JAXBException, FileNotFoundException {

        JAXBContext context = JAXBContext.newInstance(CompaniesWrapperDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        CompaniesWrapperDTO companyDTOs =
                (CompaniesWrapperDTO) unmarshaller.unmarshal(new FileReader(COMPANIES_PATH.toFile()));

        companyDTOs.getCompanies()
                .stream()
                .filter(validator::isValid)
                .map(companyImportDTO -> this.modelMapper.map(companyImportDTO, Company.class))
                .forEach(companyRepository::saveAndFlush);
    }
}
