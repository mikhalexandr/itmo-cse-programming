package itmo.cse.lab5.server.models;

import itmo.cse.lab5.common.util.Validator;

import java.util.Objects;

public class SpaceMarine implements Comparable<SpaceMarine> {
    private static final int MIN_X = -121;
    private static final int MIN_Y = -184;

    private Integer id;
    private String name;
    private final Coordinates coordinates;
    private java.util.Date creationDate;
    private final float health;
    private final long height;
    private final AstartesCategory category;
    private final MeleeWeapon meleeWeapon;
    private final Chapter chapter;

    public SpaceMarine(
            String name, Coordinates coordinates, float health, long height, AstartesCategory category,
            MeleeWeapon meleeWeapon, Chapter chapter
    ) {
        Validator.validateString(name, "SpaceMarine.name");
        Validator.validateNotNull(coordinates, "SpaceMarine.coordinates");
        Validator.validateGreaterThan(health, 0, "SpaceMarine.health");
        Validator.validateNotNull(category, "SpaceMarine.category");
        this.name = name;
        this.coordinates = coordinates;
        this.health = health;
        this.height = height;
        this.category = category;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public void validate() {
        Validator.validateId(id);
        Validator.validateString(name, "SpaceMarine.name");
        Validator.validateNotNull(coordinates, "SpaceMarine.coordinates");
        Validator.validateGreaterThan(coordinates.getX(), MIN_X, "Coordinates.x");
        Validator.validateGreaterThan(coordinates.getY(), MIN_Y, "Coordinates.y");
        Validator.validateNotNull(creationDate, "SpaceMarine.creationDate");
        Validator.validateGreaterThan(health, 0, "SpaceMarine.health");
        Validator.validateNotNull(category, "SpaceMarine.category");
        if (chapter != null) {
            Validator.validateString(chapter.getName(), "Chapter.name");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Validator.validateId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateString(name, "SpaceMarine.name");
        this.name = name;
    }


    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        Validator.validateNotNull(creationDate, "SpaceMarine.creationDate");
        this.creationDate = creationDate;
    }

    public float getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpaceMarine that = (SpaceMarine) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                "SpaceMarine{id=%d, name='%s', coordinates=%s, creationDate=%s, "
                        + "health=%.1f, height=%d, category=%s, meleeWeapon=%s, chapter=%s}",
                id, name, coordinates, creationDate,
                health, height, category, meleeWeapon, chapter
        );
    }
}
