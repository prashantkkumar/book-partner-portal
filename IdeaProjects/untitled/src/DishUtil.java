import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {

    public static List<Dish> getDishesByCuisine(List<Dish> dishes, String cuisine) {
        return dishes.stream()
                .filter(d -> d.getCuisine().equalsIgnoreCase(cuisine))
                .collect(Collectors.toList());
    }
}
