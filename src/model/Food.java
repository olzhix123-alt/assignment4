package model;

public class Food extends MenuItem {
    private String ingredients;

    public Food(int id, String name, double price, String ingredients) {
        super(id, name, price);
        this.ingredients = ingredients;
    }

    @Override
    public String getDescription() {
        return "Тағам: " + getName() + " (Құрамы: " + ingredients + ") - " + getPrice() + " тг";
    }

    public String getIngredients() { return ingredients; }
}
