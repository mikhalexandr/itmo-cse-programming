import data_types.GroundType;
import data_types.Location;
import data_types.PlantColor;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;
import implementation.Fog;
import implementation.Ground;
import implementation.PotatoPlant;
import implementation.Skuperfield;


public class Main {
    public static void main(String[] args) {
        StringBuilder output = new StringBuilder();
        
        Ground ground = new Ground(GroundType.LOOSE);

        Fog fog = new Fog();
        ground.setFog(fog);

        try {
            output.append(ground.dissipateFog());
        } catch (InvalidActionException e) {
            output.append(e.getMessage());
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
            output.append(skuperfield.move(ground));
        } catch (InvalidActionException e) {
            output.append(e.getMessage());
        }

        try {
            output.append(skuperfield.uprootPlant(ground));
        } catch (InvalidActionException | PlantNotFoundException e) {
            output.append(e.getMessage());
        }

        try {
            output.append(skuperfield.examine(ground));
            output.append(skuperfield.realizePotatoGrowInGround(ground));
        } catch (PlantNotFoundException e) {
            output.append(e.getMessage());
        }

        try {
            output.append(skuperfield.tryToEat(ground));
        } catch (PlantNotFoundException | DisgustingTasteException e) {
            output.append(e.getMessage());
        }

        try {
            output.append(skuperfield.putInPocket(ground, 5));
        } catch (PlantNotFoundException | InventoryFullException e) {
            output.append(e.getMessage());
        }

        try {
            output.append(skuperfield.move(ground));
        } catch (InvalidActionException e) {
            output.append(e.getMessage());
        }
        
        String s = output.toString();
        int max = 125;
        for (int i = 0; i < s.length(); i += max) {
            System.out.println(s.substring(i, Math.min(i + max, s.length())));
        }
    }
}
