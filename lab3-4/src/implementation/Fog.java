package implementation;

import exceptions.InvalidActionException;

import java.util.Objects;

public class Fog {
    private boolean isDense;
    
    public Fog() {
        this.isDense = true;
    }
    
    public String dissipate() throws InvalidActionException {
        if (!isDense) {
            throw new InvalidActionException("Тумана сейчас нет!");
        } else {
            this.isDense = false;
            return "Туман рассеялся. ";
        }
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
