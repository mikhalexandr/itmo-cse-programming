package model;

import types.Location;
import types.PlantColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PotatoPlant extends Plant {
    private final List<PotatoTuber> tubers;
    
    public PotatoPlant(Location loc, PlantColor color, boolean isBrittle) {
        super("клубень", color, loc, isBrittle);
        this.tubers = new ArrayList<>();

        java.util.Random random = new java.util.Random();
        int tuberCount = 3 + random.nextInt(4);
        for (int i = 0; i < tuberCount; i++) {
            tubers.add(new PotatoTuber(0.1 + i * 0.05));
        }
    }

    @Override
    public boolean hasFruit() {
        return !tubers.isEmpty();
    }

    @Override
    public List<Item> harvest(int amount) {
        List<Item> harvestedItems = new ArrayList<>();

        int countToTake = Math.min(amount, tubers.size());

        for (int i = 0; i < countToTake; i++) {
            PotatoTuber tuber = tubers.remove(0);
            harvestedItems.add(tuber);
        }

        return harvestedItems;
    }

    @Override
    public Item getSampleItem() {
        if (!tubers.isEmpty()) {
            return tubers.get(0);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (brittle: " + isBrittle + ", tubers: " + tubers.size() + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PotatoPlant that = (PotatoPlant) o;
        return isBrittle == that.isBrittle && Objects.equals(tubers, that.tubers);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tubers, isBrittle);
    }
}
