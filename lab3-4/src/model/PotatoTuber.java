package model;

import interfaces.Cleanable;
import interfaces.Edible;
import types.Taste;

import java.util.Objects;

public class PotatoTuber extends Item implements Cleanable, Edible {
    private final boolean isCooked;
    private boolean isClean;
    
    public PotatoTuber(double weight) {
        super("картофель", weight);
        this.isCooked = false;
        this.isClean = false;
    }

    @Override
    public Taste getTaste() {
        if (isCooked) {
            return Taste.DELICIOUS;
        }
        return Taste.DISGUSTING;
    }
    
    @Override
    public void clean() {
        this.isClean = true;
    }
    
    @Override
    public boolean isClean() {
        return isClean;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (cooked: " + isCooked + ", clean: " + isClean + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PotatoTuber that = (PotatoTuber) o;
        return isCooked == that.isCooked && isClean == that.isClean;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isCooked, isClean);
    }
}
