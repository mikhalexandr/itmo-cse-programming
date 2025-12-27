import base_classes.Plant;
import data_types.GroundType;
import data_types.Location;
import data_types.PlantColor;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;
import implementation.*;


public class Main {
    public static void main(String[] args) {
        Ground ground = new Ground(GroundType.LOOSE);

        Fog fog = new Fog();
        ground.setFog(fog);

        try {
            ground.dissipateFog();
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }

        Location plantLoc1 = new Location(1, 0);
        Location plantLoc2 = new Location(0, 1);
        Location plantLoc3 = new Location(1, 1);
        PotatoPlant potatoPlant1 = new PotatoPlant(plantLoc1, PlantColor.DARK_GREEN, true);
        PotatoPlant potatoPlant2 = new PotatoPlant(plantLoc2, PlantColor.DARK_GREEN, true);
        PotatoPlant potatoPlant3 = new PotatoPlant(plantLoc3, PlantColor.DARK_GREEN, true);
        ground.addPlant(potatoPlant1);
        ground.addPlant(potatoPlant2);
        ground.addPlant(potatoPlant3);

        Location startLoc = new Location(0, 0);
        Skuperfield skuperfield = new Skuperfield(startLoc);

        try {
            skuperfield.move(ground);
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }

        Plant plant = null;
        try {
            plant = skuperfield.uprootPlant(ground);
        } catch (InvalidActionException | PlantNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assert plant != null;
        PotatoTuber tuber = plant.getTubers().get(0);
        try {
            tuber = (PotatoTuber) skuperfield.examine(plant);
            skuperfield.realizePotatoGrowInGround(tuber);
        } catch (PlantNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            assert tuber != null;
            skuperfield.tryToEat(tuber);
        } catch (PlantNotFoundException | DisgustingTasteException e) {
            System.out.println(e.getMessage());
        }

        try {
            skuperfield.putInPocket((PotatoPlant) plant, 5);
        } catch (PlantNotFoundException | InventoryFullException e) {
            System.out.println(e.getMessage());
        }

        try {
            skuperfield.move(ground);
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
    }
}
