package itmo.cse.lab5.server.managers;

import itmo.cse.lab5.server.models.AstartesCategory;
import itmo.cse.lab5.server.models.SpaceMarine;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CollectionManager {
    private LinkedList<SpaceMarine> collection = new LinkedList<>();
    private final Date intializationDate = new Date();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private int generateId() {
        return nextId.getAndIncrement();
    }

    public void add(SpaceMarine spaceMarine) {
        spaceMarine.setId(generateId());
        spaceMarine.setCreationDate(new Date());
        collection.add(spaceMarine);
    }

    public void update(int id, SpaceMarine newSpaceMarine) {
        SpaceMarine old = getById(id);
        if (old == null) {
            throw new NoSuchElementException(
                    String.format("SpaceMarine с id %d не найден", id)
            );
        }
        newSpaceMarine.setId(id);
        newSpaceMarine.setCreationDate(old.getCreationDate());
        collection.set(collection.indexOf(old), newSpaceMarine);
    }

    public SpaceMarine getById(int id) {
        return collection.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean removeById(int id) {
        return collection.removeIf(x -> x.getId() == id);
    }

    public void clear() {
        collection.clear();
    }

    public SpaceMarine head() {
        return collection.peekFirst();
    }

    public boolean addIfMin(SpaceMarine spaceMarine) {
        if (collection.isEmpty() || spaceMarine.compareTo(Collections.min(collection)) < 0) {
            add(spaceMarine);
            return true;
        }
        return false;
    }

    public float sumOfHealth() {
        return (float) collection.stream()
                .mapToDouble(SpaceMarine::getHealth)
                .sum();
    }

    public SpaceMarine maxByChapter() {
        return collection.stream()
                .filter(x -> x.getChapter() != null)
                .max(Comparator.comparing(SpaceMarine::getChapter))
                .orElse(null);
    }

    public long countByCategory(AstartesCategory category) {
        return collection.stream()
                .filter(x -> x.getCategory() == category)
                .count();
    }

    public LinkedList<SpaceMarine> getCollection() {
        return collection;
    }

    public void setCollection(LinkedList<SpaceMarine> loaded) {
        this.collection = loaded;
        int maxId = collection.stream()
                .mapToInt(SpaceMarine::getId)
                .max()
                .orElse(0);
        nextId.set(maxId);
    }

    public int size() {
        return collection.size();
    }

    public String getType() {
        return collection.getClass().getSimpleName();
    }

    public Date getIntializationDate() {
        return intializationDate;
    }
}
