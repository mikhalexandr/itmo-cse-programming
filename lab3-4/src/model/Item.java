package model;

import java.util.Objects;

public abstract class Item {
    protected String name;
    protected double weight;
    
    public Item(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + " (weight: " + weight + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.weight, weight) == 0 && Objects.equals(name, item.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
