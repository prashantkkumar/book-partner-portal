public class Dish {
    private String name;
    private String cuisine;
    private double price;
    private double rating;

    public Dish(String name, String cuisine, double price, double rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.price = price;
        this.rating = rating;
    }

    public String getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        return name + "|" + cuisine + "|" + price + "|" + rating;
    }
}
