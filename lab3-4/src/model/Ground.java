package model;

import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import types.GroundType;
import types.Location;

import java.util.*;

public class Ground {
    private final GroundType type;
    private final List<Plant> plants;
    private Fog fog;
    
    public Ground(GroundType type) {
        this.type = Objects.requireNonNull(type, "Ground type не может быть null!");
        this.plants = new ArrayList<>();
        this.fog = null;
    }

    public void setFog(Fog fog) {
        this.fog = fog;
    }

    public void dissipateFog() throws InvalidActionException {
        if (fog == null) {
            throw new InvalidActionException("Туман оказался фантомным...");
        }
        fog.dissipate();
    }
    
    public void addPlant(Plant plant) {
        plants.add(Objects.requireNonNull(plant, "Plant не может быть null!"));
    }

    public String getDominantColor() {
        if (plants.isEmpty()) {
            return null;
        }
        Map<String, Integer> colorCount = new HashMap<>();
        for (Plant plant : plants) {
            String color = plant.getColor().name();
            colorCount.merge(color, 1, Integer::sum);
        }
        return colorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public boolean hasMajorityBrittlePlants() {
        if (plants.isEmpty()) {
            return false;
        }
        long brittleCount = plants.stream()
                .filter(Plant::isBrittle)
                .count();
        return brittleCount > plants.size() / 2;
    }

    public int getPlantCount() {
        return plants.size();
    }
    
    public Plant getPlantAt(Location loc) throws PlantNotFoundException {
        Objects.requireNonNull(loc, "Location не может быть null!");
        
        return plants.stream()
                .filter(plant -> loc.equals(plant.getLocation()))
                .findFirst()
                .orElseThrow(() -> new PlantNotFoundException(
                    "Скуперфильд начал вырывать фантомные растения (" + loc + ")"));
    }
    
    public boolean isDifficultToMove() {
        return type == GroundType.LOOSE;
    }

    @Override
    public String toString() {
        return String.format("Ground (type: %s, plants: %d, fog: %s)", 
            type, plants.size(), fog != null ? fog : "none");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ground ground = (Ground) o;
        return type == ground.type && 
               Objects.equals(plants, ground.plants) && 
               Objects.equals(fog, ground.fog);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, plants, fog);
    }
}
