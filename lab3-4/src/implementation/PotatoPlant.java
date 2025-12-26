package implementation;

import base_classes.Plant;
import data_types.Location;
import data_types.PlantColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PotatoPlant extends Plant {
    private final List<PotatoTuber> tubers;
    
    public PotatoPlant(Location loc, PlantColor color, boolean isBrittle) {
        super("PotatoPlant", color, loc, isBrittle);
        this.tubers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            tubers.add(new PotatoTuber(0.1 + i * 0.05));
        }
    }
    
    public List<PotatoTuber> getTubers() {
        return new ArrayList<>(tubers);
    }
    
    public void removeTuber(PotatoTuber tuber) {
        tubers.remove(tuber);
    }
    
    @Override
    public boolean hasFruit() {
        return !tubers.isEmpty();
    }
    
    @Override
    public String examine() {
        return "Желтоватый клубень";
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

