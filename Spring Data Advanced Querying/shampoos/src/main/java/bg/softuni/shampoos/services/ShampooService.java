package bg.softuni.shampoos.services;

import bg.softuni.shampoos.entities.Shampoo;

import java.util.List;

public interface ShampooService {

    List<Shampoo> findAllBySize(String size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(String size, long label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(String price);

    int countAllByPriceLessThan(String price);

    List<Shampoo> findAllByIngredientsIncluded(String... ingredients);

    //List<Shampoo> findAllByIngredientCountLessThan(int count);
}
