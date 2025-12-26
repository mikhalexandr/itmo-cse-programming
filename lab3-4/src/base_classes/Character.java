package base_classes;

import data_types.Location;
import data_types.Mood;
import exceptions.InvalidActionException;
import implementation.Ground;
import implementation.Inventory;

import java.util.Objects;

public abstract class Character {
    protected String name;
    protected Mood mood;
    protected Location currentLocation;
    protected Inventory inventory;
    
    public Character(String name, Location startLoc) {
        this.name = name;
        this.mood = Mood.NORMAL;
        this.currentLocation = startLoc;
        this.inventory = new Inventory(20);
    }
    
    public abstract String move(Ground ground) throws InvalidActionException;
    
    public void setLocation(Location loc) {
        this.currentLocation = loc;
    }

    protected void setMood(Mood mood) {
        this.mood = mood;
    }

    public Mood getMood() {
        return mood;
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    @Override
    public String toString() {
        return name + " (mood: " + mood + ", location: " + currentLocation + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && 
               mood == character.mood && 
               Objects.equals(currentLocation, character.currentLocation);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, mood, currentLocation);
    }
}
