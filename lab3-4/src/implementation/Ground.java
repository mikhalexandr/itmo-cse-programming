package implementation;

import base_classes.Plant;
import data_types.GroundType;
import data_types.Location;
import exceptions.PlantNotFoundException;
import java.util.ArrayList;
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
    
    public void addPlant(Plant plant) {
        plants.add(plant);
    }
    
    public Plant getPlantAt(Location loc) throws PlantNotFoundException {
        for (Plant plant : plants) {
            if (plant.getLocation().equals(loc)) {
                return plant;
            }
        }
        throw new PlantNotFoundException("No plant found at location " + loc);
    }
    
    public boolean isDifficultToMove() {
        return type == GroundType.LOOSE;
    }
    
    public GroundType getType() {
        return type;
    }
    
    public void setFog(Fog fog) {
        this.fog = fog;
    }
    
    public Fog getFog() {
        return fog;
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
