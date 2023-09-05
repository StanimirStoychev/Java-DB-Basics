package com.example.productsshop.services.seed;

import com.example.productsshop.domain.dtos.category.CategoryImportDTO;
import com.example.productsshop.domain.dtos.category.wrappers.CategoriesWrapperDTO;
import com.example.productsshop.domain.dtos.product.ProductImportDTO;
import com.example.productsshop.domain.dtos.user.UserImportDTO;
import com.example.productsshop.domain.dtos.user.wrappers.UsersWrapperDTO;
import com.example.productsshop.domain.entities.Category;
import com.example.productsshop.domain.entities.Product;
import com.example.productsshop.domain.entities.User;
import com.example.productsshop.repositories.CategoryRepository;
import com.example.productsshop.repositories.ProductRepository;
import com.example.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

import static com.example.productsshop.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(Gson gson, UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {

        if (this.userRepository.count() == 0) {
//            FileReader reader = new FileReader(USERS_JSON_PATH.toFile());
//
//            List<User> users = Arrays.stream(gson.fromJson(reader, UserImportDTO[].class))
//                    .map(userImportDTO -> modelMapper.map(userImportDTO, User.class)).toList();

            FileReader reader = new FileReader(USERS_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(UsersWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            UsersWrapperDTO usersWrapperDTO = (UsersWrapperDTO) unmarshaller.unmarshal(reader);
            List<User> users = usersWrapperDTO.getUsers()
                    .stream()
                    .map(userImportDTO -> modelMapper.map(userImportDTO, User.class))
                    .toList();

            this.userRepository.saveAllAndFlush(users);
        }
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {

        if (this.categoryRepository.count() == 0) {
//            FileReader reader = new FileReader(CATEGORIES_JSON_PATH.toFile());

//            List<Category> categories = Arrays.stream(gson.fromJson(reader, CategoryImportDTO[].class))
//                    .map(categoryImportDTO -> modelMapper.map(categoryImportDTO, Category.class)).toList();

            FileReader reader = new FileReader(CATEGORIES_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(CategoriesWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            CategoriesWrapperDTO categoriesWrapperDTO = (CategoriesWrapperDTO) unmarshaller.unmarshal(reader);
            List<Category> categories = categoriesWrapperDTO
                    .getCategories()
                    .stream()
                    .map(categoryImportDTO -> modelMapper.map(categoryImportDTO, Category.class))
                    .toList();

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        if (this.productRepository.count() == 0) {
            FileReader reader = new FileReader(PRODUCTS_JSON_PATH.toFile());

            List<Product> products = Arrays.stream(gson.fromJson(reader, ProductImportDTO[].class))
                    .map(productImportDTO -> modelMapper.map(productImportDTO, Product.class))
                    .map(this::setRandomCategories)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomSeller)
                    .toList();

            this.productRepository.saveAllAndFlush(products);
        }
    }

    private Product setRandomCategories(Product product) {
        final Random random = new Random();

        long countOfCategories = random.nextLong(this.categoryRepository.count() - 2);

        Set<Category> categories = new HashSet<>();

//        IntStream.range(0, countOfCategories)
//                .forEach(value -> {
//                    categories.add(this.categoryRepository
//                            .getRandomEntity()
//                            .orElseThrow(NoSuchElementException::new));
//                });

        for (int i = 0; i < countOfCategories; i++) {
            categories.add(this.categoryRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new));
        }

        product.setCategories(categories);

        return product;
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository
                .getRandomEntity()
                .orElseThrow(NoSuchElementException::new);

        while (product.getBuyer() != null && product.getBuyer().getId().equals(seller.getId())) {
            seller = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);
        }

        product.setSeller(seller);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(750)) > 0) {
            final User buyer = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);

            product.setBuyer(buyer);
        }

        return product;
    }
}
