package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.util.validator.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.CITIES_PATH;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtils validator;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validator) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(CITIES_PATH);
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();

        CityImportDTO[] cityImportDTOS = this.gson.fromJson(readCitiesFileContent(), CityImportDTO[].class);

        for (CityImportDTO cityImportDTO : cityImportDTOS) {
            boolean isValid = this.validator.isValid(cityImportDTO);

            if (isValid) {

                City city = this.modelMapper.map(cityImportDTO, City.class);

                if (this.cityRepository.findByCityName(city.getCityName()).isEmpty()) {

                    this.cityRepository.saveAndFlush(city);
                    sb.append(SUCCESSFUL + CITY + " " + city.getCityName() + " - " + city.getPopulation())
                            .append(System.lineSeparator());

                } else {
                    sb.append(INVALID + CITY).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + CITY).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
