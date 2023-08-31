package bg.softuni.cardealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDateTime> localDateTimeConverter = context -> LocalDateTime.parse(context.getSource());
        modelMapper.addConverter(localDateTimeConverter, String.class, LocalDateTime.class);

        return modelMapper;
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
