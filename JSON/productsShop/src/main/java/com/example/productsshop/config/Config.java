package com.example.productsshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    void createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
    }

    @Bean
    void createGSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
