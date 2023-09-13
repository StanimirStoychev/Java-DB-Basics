package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.util.validator.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.COUNTRIES_PATH;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtils validator;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validator) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(COUNTRIES_PATH);
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        CountryImportDTO[] countryImportDTOS = gson.fromJson(readCountriesFromFile(), CountryImportDTO[].class);

        for (CountryImportDTO countryImportDTO : countryImportDTOS) {
            boolean isValid = this.validator.isValid(countryImportDTO);

            if (isValid) {

                Country country = this.modelMapper.map(countryImportDTO, Country.class);

                if (this.countryRepository.findByCountryName(country.getCountryName()).isEmpty()) {

                    this.countryRepository.saveAndFlush(country);
                    sb.append(SUCCESSFUL + COUNTRY + " " + country.getCountryName() + " - " + country.getCurrency())
                            .append(System.lineSeparator());

                } else {
                    sb.append(INVALID + COUNTRY).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + COUNTRY).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
