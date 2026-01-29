package model;

public class Drink extends MenuItem {
    private double volume;

    public Drink(int id, String name, double price, double volume) {
        super(id, name, price);
        this.volume = volume;
    }

    @Override
    public String getDescription() {
        return "Сусын: " + getName() + " (Көлемі: " + volume + "л) - " + getPrice() + " тг";
    }

    public double getVolume() { return volume; }
}
