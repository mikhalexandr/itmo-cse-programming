import data_types.GroundType;
import data_types.Location;
import data_types.PlantColor;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import implementation.*;


public class Main {
    public static void main(String[] args) {
        Ground ground = new Ground(GroundType.LOOSE);

        Fog fog = new Fog();
        ground.setFog(fog);

        try {
            System.out.print(ground.dissipateFog());
        } catch (InvalidActionException e) {
            System.out.print(e.getMessage());
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

        System.out.print(skuperfield.move(ground));

        try {
            System.out.print(skuperfield.uprootPlant(ground));
        } catch (InvalidActionException | PlantNotFoundException e) {
            System.out.print(e.getMessage());
        }

        try {
            System.out.print(skuperfield.examine(ground));
            System.out.print(skuperfield.realizePotatoGrowInGround(ground));
        } catch (PlantNotFoundException e) {
            System.out.print(e.getMessage());
        }

        try {
            System.out.print(skuperfield.tryToEat(ground));
        } catch (PlantNotFoundException | DisgustingTasteException e) {
            System.out.print(e.getMessage());
        }

        try {
            System.out.print(skuperfield.putInPocket(ground, 5));
        } catch (PlantNotFoundException e) {
            System.out.print(e.getMessage());
        }

        System.out.print(skuperfield.move(ground));

    }
}
