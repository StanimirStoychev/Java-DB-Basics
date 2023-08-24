package com.example.productsshop.services;

import com.example.productsshop.domain.dtos.user.UserWithSoldProductsDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName() throws IOException;
}
