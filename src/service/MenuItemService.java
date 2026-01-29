package service;

import exception.ResourceNotFoundException;
import model.MenuItem;
import repository.CrudRepository;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemService {
    private final CrudRepository<MenuItem> repository;

    public MenuItemService(CrudRepository<MenuItem> repository) {
        this.repository = repository;
    }

    public void addItem(MenuItem item) {
        repository.create(item);
    }

    public List<MenuItem> getAllItems() {
        return repository.findAll();
    }

    public List<MenuItem> getItemsSortedByPrice() {
        List<MenuItem> items = repository.findAll();
        items.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        return items;
    }

    public MenuItem getItemById(int id) throws ResourceNotFoundException {
        MenuItem item = repository.findById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Menu item with ID " + id + " not found.");
        }
        return item;
    }

    public void removeItem(int id) {
        repository.delete(id);
    }
}
