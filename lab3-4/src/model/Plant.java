package model;

import interfaces.Uprootable;
import types.Location;
import types.PlantColor;

import java.util.List;
import java.util.Objects;

public abstract class Plant implements Uprootable {
    protected String species;
    protected PlantColor color;
    protected boolean isUprooted;
    protected boolean isBrittle;
    protected Location location;
    
    public Plant(String species, PlantColor color, Location loc,  boolean isBrittle) {
        this.species = species;
        this.color = color;
        this.location = loc;
        this.isUprooted = false;
        this.isBrittle = isBrittle;
    }
    
    public abstract boolean hasFruit();

    public abstract List<Item> harvest(int amount);

    public abstract Item getSampleItem();
    
    public Location getLocation() {
        return location;
    }
    
    public PlantColor getColor() {
        return color;
    }

    public String getName() {
        return species;
    }

    public boolean isBrittle() {
        return isBrittle;
    }
    
    @Override
    public void uproot() {
        this.isUprooted = true;
    }
    
    @Override
    public boolean isUprooted() {
        return isUprooted;
    }
    
    @Override
    public String toString() {
        return species + " (color: " + color +
               ", location: " + location +
               ", uprooted: " + isUprooted +
               ", brittle: " + isBrittle + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return isUprooted == plant.isUprooted &&
               isBrittle == plant.isBrittle &&
               Objects.equals(species, plant.species) &&
               color == plant.color &&
               Objects.equals(location, plant.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, color, isUprooted, isBrittle, location);
    }
}
