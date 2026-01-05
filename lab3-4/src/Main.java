import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;
import model.*;
import types.GroundType;
import types.Location;
import types.PlantColor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. НАСТРОЙКА МИРА
        Ground ground = new Ground(GroundType.LOOSE);
        ground.setFog(new Fog());

        ground.addPlant(new PotatoPlant(new Location(1, 0), PlantColor.DARK_GREEN, true));
        ground.addPlant(new PotatoPlant(new Location(0, 1), PlantColor.DARK_GREEN, true));
        ground.addPlant(new PotatoPlant(new Location(1, 1), PlantColor.DARK_GREEN, true));

        Skuperfield skuperfield = new Skuperfield(new Location(0, 0));

        // 2. СЦЕНАРИЙ
        try {
            try {
                ground.dissipateFog();
            } catch (InvalidActionException e) {
                System.out.println(e.getMessage());
            }

            skuperfield.move(ground);

            Plant plant = skuperfield.uprootPlant(ground);

            Item sampleItem = skuperfield.examine(plant);

            if (sampleItem != null) {
                skuperfield.realizeItemGrowInGround(sampleItem);

                try {
                    skuperfield.tryToEat(sampleItem);
                } catch (DisgustingTasteException e) {
                    System.out.println(e.getMessage());
                }

                List<Item> loot = plant.harvest(5);

                if (!loot.isEmpty()) {
                    try {
                        skuperfield.putInPocket(loot);
                    } catch (InventoryFullException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Наш герой отправился дальше.");
                } else {
                    System.out.println("На корнях ничего не осталось. Наш герой, расстроенный этим, отправился дальше.");
                }
            } else {
                System.out.println("Наш герой выбросил бесполезное растение.");
            }

            skuperfield.move(ground);

        } catch (PlantNotFoundException | InvalidActionException e) {
            System.out.println("История прерывается: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}
