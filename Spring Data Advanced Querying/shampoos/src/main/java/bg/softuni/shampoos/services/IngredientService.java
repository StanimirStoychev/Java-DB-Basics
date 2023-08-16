package bg.softuni.shampoos.services;

import bg.softuni.shampoos.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> findAllByNameStartingWith(String name);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> names);

    void deleteIngredientsByName(String name);

    //void updateIngredientsByPrice();

    //void upgradeIngredientsByName(String name);
}
