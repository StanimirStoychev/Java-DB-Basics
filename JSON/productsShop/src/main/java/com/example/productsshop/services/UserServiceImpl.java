package com.example.productsshop.services;

import com.example.productsshop.domain.dtos.user.UserWithSoldProductsDTO;
import com.example.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productsshop.constants.Paths.USER_SOLD_PRODUCTS_OUTPUT_PATH;
import static com.example.productsshop.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<UserWithSoldProductsDTO> findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName() throws IOException {
        List<UserWithSoldProductsDTO> users = this.userRepository.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDTO.class))
                .toList();

        writeIntoJsonFile(users, USER_SOLD_PRODUCTS_OUTPUT_PATH);

        return users;
    }
}
