package com.example.productsshop.services.user;

import com.example.productsshop.domain.dtos.user.UserDTO;
import com.example.productsshop.domain.dtos.user.UserWithProductsDTO;
import com.example.productsshop.domain.dtos.user.UserWithSoldProductsDTO;
import com.example.productsshop.domain.dtos.user.UsersWithSoldProductsXMLDTO;
import com.example.productsshop.domain.dtos.user.wrappers.UsersWithProductsWrapperDTO;
import com.example.productsshop.domain.dtos.user.wrappers.UsersWrapperXMLDTO;
import com.example.productsshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productsshop.constants.Paths.*;
import static com.example.productsshop.constants.Utils.writeIntoJsonFile;
import static com.example.productsshop.constants.Utils.writeIntoXmlFile;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<UserWithSoldProductsDTO> writeUsersWithSoldProducts() throws IOException, JAXBException {
        List<UserWithSoldProductsDTO> users = this.userRepository.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDTO.class))
                .toList();

        writeIntoJsonFile(users, USER_SOLD_PRODUCTS_OUTPUT_PATH);

        List<UsersWithSoldProductsXMLDTO> usersWithSoldProductsXMLDTOS = this.userRepository.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> modelMapper.map(user, UsersWithSoldProductsXMLDTO.class))
                .toList();

        UsersWrapperXMLDTO users1 = new UsersWrapperXMLDTO(usersWithSoldProductsXMLDTOS);

        writeIntoXmlFile(users1, USER_SOLD_PRODUCTS_XML_OUTPUT_PATH);

        return users;
    }
    @Override
    public UsersWithProductsWrapperDTO usersAndProducts() throws IOException {
        final List<UserWithProductsDTO> usersAndProducts =
                this.userRepository.findAllBySellProductsBuyerIsNotNullOrderBySellProductsBuyerLastName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO)
                .toList();

        UsersWithProductsWrapperDTO usersWithProductsWrapperDTO = new UsersWithProductsWrapperDTO(usersAndProducts);

        writeIntoJsonFile(usersWithProductsWrapperDTO, USERS_WITH_PRODUCTS_OUTPUT_PATH);

        return usersWithProductsWrapperDTO;
    }
}
