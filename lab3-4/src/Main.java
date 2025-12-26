import data_types.GroundType;
import data_types.Location;
import data_types.PlantColor;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import implementation.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws PlantNotFoundException {
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

        skuperfield.move(ground);

        try {
            skuperfield.uprootPlant(ground);
        } catch (InvalidActionException | PlantNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Location currLoc = skuperfield.getCurrentLocation();
        PotatoPlant plant = (PotatoPlant) ground.getPlantAt(currLoc);

        List<PotatoTuber> tubers = plant.getTubers();
        if (!tubers.isEmpty()) {
            System.out.println("он увидел несколько прицепившихся к корням желтоватых клубней.");
        }

        String examination = plant.examine();
        skuperfield.realizePotatoGrowInGround();

        // Скуперфильд пытается съесть сырой картофель
        System.out.println("\n...откусил кусочек... Сырой картофель показался ему страшно невкусным...");
        if (!tubers.isEmpty()) {
            PotatoTuber tuber = tubers.get(0);
            try {
                skuperfield.tryToEat(tuber);
                System.out.println("Скуперфильд съел клубень");
            } catch (DisgustingTasteException e) {
                System.out.println("Скуперфильд выплюнул кусок: " + e.getMessage());
            }
        }

        // Скуперфильд кладет картофель в карман
        System.out.println("\n...сунул вытащенные из земли полдесятка картофелин в карман пиджака...");
        int count = Math.min(5, tubers.size());
        for (int i = 0; i < count; i++) {
            try {
                PotatoTuber tuber = tubers.get(i);
                skuperfield.getInventory().addItem(tuber);
                potatoPlant.removeTuber(tuber);
                System.out.println("Клубень " + (i + 1) + " добавлен в инвентарь");
            } catch (Exception e) {
                System.out.println("Ошибка при добавлении клубня: " + e.getMessage());
            }
        }

        tubers = potatoPlant.getTubers();
        System.out.println("Инвентарь: " + skuperfield.getInventory());

        skuperfield.move(ground);
    }
}
