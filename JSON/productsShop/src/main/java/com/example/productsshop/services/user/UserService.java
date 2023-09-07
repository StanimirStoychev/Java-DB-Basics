package com.example.productsshop.services.user;

import com.example.productsshop.domain.dtos.user.UserWithSoldProductsDTO;
import com.example.productsshop.domain.dtos.user.wrappers.UsersWithProductsWrapperDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> writeUsersWithSoldProducts() throws IOException, JAXBException;

    UsersWithProductsWrapperDTO usersAndProducts() throws IOException;
}
