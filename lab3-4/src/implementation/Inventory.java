package implementation;

import base_classes.Item;
import exceptions.InventoryFullException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
    private final int maxCapacity;
    private final List<Item> items;
    
    public Inventory(int capacity) {
        this.maxCapacity = capacity;
        this.items = new ArrayList<>();
    }
    
    public void addItem(Item item) throws InventoryFullException {
        if (items.size() >= maxCapacity) {
            throw new InventoryFullException("Карман полон! Максимальная вместимость: " + maxCapacity);
        }
        items.add(item);
    }

    @Override
    public String toString() {
        return "Inventory (size: " + items.size() + "/" + maxCapacity + ", items: " + items + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return maxCapacity == inventory.maxCapacity && Objects.equals(items, inventory.items);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(maxCapacity, items);
    }
}
