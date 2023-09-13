package softuni.exam.service;

import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.forecast.ForecastImportDTO;
import softuni.exam.models.dto.forecast.ForecastsWrapperDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.util.validator.ValidationUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Messages.*;
import static softuni.exam.constants.Paths.FORECASTS_PATH;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtils validator;
    private final Unmarshaller unmarshaller;

    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository,
                               ModelMapper modelMapper, ValidationUtils validator) throws JAXBException {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ForecastsWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(FORECASTS_PATH);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        ForecastsWrapperDTO forecastsWrapperDTO =
                (ForecastsWrapperDTO) this.unmarshaller.unmarshal(new FileReader(FORECASTS_PATH.toFile()));
        List<ForecastImportDTO> forecastImportDTOS = forecastsWrapperDTO.getForecasts();

        for (ForecastImportDTO forecastImportDTO : forecastImportDTOS) {
            boolean isValid = this.validator.isValid(forecastImportDTO);

            if (isValid) {
                Forecast forecast = this.modelMapper.map(forecastImportDTO, Forecast.class);
                City city = this.cityRepository.findById(forecastImportDTO.getCity()).get();
                forecast.setCity(city);

                if (this.forecastRepository.findByDayOfWeekAndCityId(forecast.getDayOfWeek(),
                        forecast.getCity().getId()).isEmpty()) {

                    this.forecastRepository.saveAndFlush(forecast);
                    sb.append(SUCCESSFUL + FORECAST + " " + forecast.getDayOfWeek().toString() + " - " +
                            forecast.getMaxTemperature()).append(System.lineSeparator());

                } else {
                    sb.append(INVALID + FORECAST).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + FORECAST).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
