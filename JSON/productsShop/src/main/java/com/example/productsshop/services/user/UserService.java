package com.example.productsshop.services.user;

import com.example.productsshop.domain.dtos.user.UserWithSoldProductsDTO;
import com.example.productsshop.domain.dtos.user.wrappers.UsersWithProductsWrapperDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName() throws IOException;

    UsersWithProductsWrapperDTO usersAndProducts() throws IOException;
}
