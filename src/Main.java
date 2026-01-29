import model.Drink;
import model.Food;
import model.MenuItem;
import repository.MenuItemRepository;
import service.MenuItemService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MenuItemRepository repository = new MenuItemRepository();
        MenuItemService service = new MenuItemService(repository);

        Food pizza = new Food(0, "Pizza", 2500, "Cheese, Tomato");
        Drink cola = new Drink(0, "Cola", 580, 0.5);


        // service.addItem(pizza);
        // service.addItem(cola);

        System.out.println("--- All Items ---");
        List<MenuItem> allItems = service.getAllItems();
        for (MenuItem item : allItems) {
            System.out.println(item.getDescription());
        }


        System.out.println("--- Lambda ---");
        service.getItemsSortedByPrice().forEach(item ->
                System.out.println(item.getName() + ": " + item.getPrice() + " тг")
        );
    }
}
