package itmo.cse.lab5.server.models;

import itmo.cse.lab5.common.util.Validator;

import java.util.Objects;

public class Coordinates {
    private double x;
    private long y;

    public Coordinates(double x, long y) {
        Validator.validateGreaterThan(x, -121, "Coordinates.x");
        Validator.validateGreaterThan(y, -184, "Coordinates.y");
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        Validator.validateGreaterThan(x, -121, "Coordinates.x");
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        Validator.validateGreaterThan(y, -184, "Coordinates.y");
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("Coordinates[x=%f,y=%d]", x, y);
    }
}
