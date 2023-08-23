package com.example.productsshop.services;

import com.example.productsshop.domain.dtos.UserImportDTO;
import com.example.productsshop.domain.entities.User;
import com.example.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static com.example.productsshop.constants.Paths.USERS_PATH;

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public SeedServiceImpl(Gson gson, UserRepository userRepository) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {

        if (this.userRepository.count() == 0) {
            FileReader reader = new FileReader(USERS_PATH.toFile());

            List<User> users = Arrays.stream(gson.fromJson(reader, UserImportDTO[].class))
                    .map(userImportDTO -> modelMapper.map(userImportDTO, User.class)).toList();

            this.userRepository.saveAllAndFlush(users);
        }
    }

    @Override
    public void seedProducts() {

    }

    @Override
    public void seedCategories() {

    }
}
