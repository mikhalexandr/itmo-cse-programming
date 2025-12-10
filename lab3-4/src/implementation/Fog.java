package implementation;

import java.util.Objects;

public class Fog {
    private boolean isDense;
    
    public Fog(boolean isDense) {
        this.isDense = isDense;
    }
    
    public void dissipate() {
        this.isDense = false;
    }
    
    public boolean isDense() {
        return isDense;
    }
    
    @Override
    public String toString() {
        return "Fog (dense: " + isDense + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fog fog = (Fog) o;
        return isDense == fog.isDense;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isDense);
    }
}

