package itmo.cse.lab5.server.models;

import itmo.cse.lab5.common.util.Validator;

import java.util.Objects;

public class Chapter implements Comparable<Chapter> {
    private String name;
    private String parentLegion;

    public Chapter(String name, String parentLegion) {
        Validator.validateString(name, "Chapter.name");
        this.name = name;
        this.parentLegion = parentLegion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateString(name, "Chapter.name");
        this.name = name;
    }
    public String getParentLegion() {
        return parentLegion;
    }

    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public int compareTo(Chapter o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter that = (Chapter) o;
        return Objects.equals(name, that.name)
                && Objects.equals(parentLegion, that.parentLegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion);
    }

    @Override
    public String toString() {
        return String.format("Chapter[name=%s, parentLegion=%s]", name, parentLegion);
    }
}
