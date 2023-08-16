package bg.softuni.shampoos;

import bg.softuni.shampoos.services.IngredientService;
import bg.softuni.shampoos.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

    //    String size = scanner.nextLine();
    //    long label = Long.parseLong(scanner.nextLine());
    //    String price = scanner.nextLine();
        String name = scanner.nextLine();
    //    List<String> names = Arrays.stream(scanner.nextLine().split("\\s+")).toList();
    //    String[] ingredients = scanner.nextLine().split("\\s+");
    //    int count = Integer.parseInt(scanner.nextLine());

    //   1.)
    //      for (Shampoo shampoo : this.shampooService.findAllBySize(size)) {
    //      System.out.println(shampoo);
    //      }

    //    2.)
    //    for (Shampoo shampoo : this.shampooService.findAllBySizeOrLabelIdOrderByPrice(size, label)) {
    //        System.out.println(shampoo);
    //    }

    //    3.)
    //    for (Shampoo shampoo : this.shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price)) {
    //        System.out.println(shampoo);
    //    }

    //    4.)
    //    for (Ingredient ingredient : this.ingredientService.findAllByNameStartingWith(name)) {
    //        System.out.println(ingredient);
    //    }

    //    5.)
    //    for (Ingredient ingredient : this.ingredientService.findAllByNameInOrderByPrice(names)) {
    //        System.out.println(ingredient);
    //    }

    //    6.)
    //    System.out.println(this.shampooService.countAllByPriceLessThan(price));

    //    7.)
    //    for (Shampoo shampoo : this.shampooService.findAllByIngredientsIncluded(ingredients)) {
    //        System.out.println(shampoo);
    //    }

    //    8.)
    //    for (Shampoo shampoo : this.shampooService.findAllByIngredientCountLessThan(count)) {
    //        System.out.println(shampoo);
    //    }

    //    9.)
    //    this.ingredientService.deleteIngredientsByName(name);

    //    10.)
    //    this.ingredientService.updateIngredientsByPrice();

    //    this.ingredientService.upgradeIngredientsByName(name);
    }
}
