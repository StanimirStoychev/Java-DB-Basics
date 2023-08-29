package bg.softuni.cardealer.config;

import bg.softuni.cardealer.domain.dtos.customer.CustomerDTO;
import bg.softuni.cardealer.domain.entities.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new StringToLocalDateTimeConverter());
        modelMapper.addConverter(new LocalDateTimeToStringConverter());
        return modelMapper;
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
