import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Dish> dishList = new ArrayList<>();

        System.out.println("Enter the number of dishes");
        int numberOfDishes = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the dish details");
        for (int i = 0; i < numberOfDishes; i++) {
            String[] input = scanner.nextLine().split(":");
            String name = input[0];
            String cuisine = input[1];
            double price = Double.parseDouble(input[2]);
            double rating = Double.parseDouble(input[3]);

            Dish dish = new Dish(name, cuisine, price, rating);
            dishList.add(dish);
        }

        System.out.println("Enter the cuisine");
        String cuisineToSearch = scanner.nextLine();

        List<Dish> filteredDishes = DishUtil.getDishesByCuisine(dishList, cuisineToSearch);

        if (!filteredDishes.isEmpty()) {
            System.out.println("Dishes of cuisine " + cuisineToSearch + " are");
            for (Dish d : filteredDishes) {
                System.out.println(d);
            }
        } else {
            System.out.println("No dishes were found for the given cuisine " + cuisineToSearch);
        }

        scanner.close();
    }
}
