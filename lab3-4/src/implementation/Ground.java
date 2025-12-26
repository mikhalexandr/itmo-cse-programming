package implementation;

import base_classes.Plant;
import data_types.GroundType;
import data_types.Location;
import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;

public class Ground {
    private GroundType type;
    private List<Plant> plants;
    private Fog fog;
    
    public Ground(GroundType type) {
        this.type = type;
        this.plants = new ArrayList<>();
        this.fog = null;
    }

    public void setFog(Fog fog) {
        this.fog = fog;
    }

    public String dissipateFog() throws InvalidActionException {
        return fog.dissipate();
    }
    
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Map<String, Integer> countPlantsByColor() {
        Map<String, Integer> colorCount = new HashMap<>();
        for (Plant plant : plants) {
            String color = String.valueOf(plant.getColor());
            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
        }
        return colorCount;
    }

    public String getDominantColor() {
        Map<String, Integer> colorCount = countPlantsByColor();
        String dominantColor = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : colorCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                dominantColor = entry.getKey();
            }
        }
        return dominantColor;
    }

    public boolean hasMajorityBrittlePlants() {
        int brittleCount = 0;
        for (Plant plant : plants) {
            if (plant.isBrittle()) {
                brittleCount++;
            }
        }
        return !plants.isEmpty() && brittleCount > plants.size() / 2;
    }

    public int getPlantCount() {
        return plants.size();
    }
    
    public Plant getPlantAt(Location loc) throws PlantNotFoundException {
        for (Plant plant : plants) {
            if (plant.getLocation().equals(loc)) {
                return plant;
            }
        }
        throw new PlantNotFoundException("Скуперфильд начал вырывать фантомные растения (" + loc + ")");
    }
    
    public boolean isDifficultToMove() {
        return type == GroundType.LOOSE;
    }
    
    public GroundType getType() {
        return type;
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }
    
    @Override
    public String toString() {
        return "Ground (type: " + type + ", plants: " + plants.size() + ", fog: " + (fog != null ? fog : "none") + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ground ground = (Ground) o;
        return type == ground.type && Objects.equals(plants, ground.plants) && Objects.equals(fog, ground.fog);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, plants, fog);
    }
}
