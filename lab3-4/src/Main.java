import data_types.GroundType;
import data_types.Location;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import implementation.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Начало истории ===");
        
        // Создаем туман
        Fog fog = new Fog(true);
        System.out.println("Туман создан: " + fog);
        
        // Туман рассеивается
        System.out.println("\nВскоре туман рассеялся...");
        fog.dissipate();
        System.out.println("Туман после рассеивания: " + fog);
        
        // Создаем землю
        Ground ground = new Ground(GroundType.LOOSE);
        ground.setFog(fog);
        System.out.println("\nЗемля создана: " + ground);
        
        // Создаем Скуперфильда
        Location startLoc = new Location(0, 0);
        Skuperfield skuperfield = new Skuperfield(startLoc);
        System.out.println("Скуперфильд создан: " + skuperfield);
        
        // Скуперфильд шагает по рыхлой земле
        System.out.println("\n...Скуперфильд обнаружил, что шагает по рыхлой земле...");

        
        // Создаем картофельное растение
        Location plantLoc = new Location(1, 0);
        PotatoPlant potatoPlant = new PotatoPlant(plantLoc);
        ground.addPlant(potatoPlant);
        System.out.println("\n...усаженной какими-то темно-зелеными, ломкими кустиками...");
        System.out.println("Картофельное растение создано: " + potatoPlant);
        System.out.println("Цвет: " + potatoPlant.getColor() + ", ломкое: " + potatoPlant.isBrittle());
        
        // Скуперфильд выдергивает растение
        System.out.println("\nВыдернув из земли один кустик...");
        skuperfield.setLocation(plantLoc);
        try {
            skuperfield.uprootPlant(ground);
            System.out.println("Растение выдернуто. isUprooted: " + potatoPlant.isUprooted());
        } catch (PlantNotFoundException | InvalidActionException e) {
            System.out.println("Ошибка при выдергивании: " + e.getMessage());
        }
        
        // Скуперфильд видит клубни
        System.out.println("\n...он увидел несколько прицепившихся к корням желтоватых клубней.");
        List<PotatoTuber> tubers = potatoPlant.getTubers();
        System.out.println("Количество клубней: " + tubers.size());
        
        // Осматривает клубни
        System.out.println("\nОсмотрев клубни внимательно...");
        String examination = potatoPlant.examine();
        System.out.println("Результат осмотра: " + examination);
        
        // Скуперфильд понимает правду о картофеле
        System.out.println("\n...Скуперфильд начал догадываться... к тому же почему-то воображал, что картофель растет на деревьях.");
        System.out.println("До осознания: hasPotatoMisconception = " + skuperfield.hasPotatoMisconception());
        skuperfield.realizePotatoGrowInGround();
        System.out.println("После осознания: hasPotatoMisconception = " + skuperfield.hasPotatoMisconception());
        
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
        // Обновляем список после удаления
        tubers = potatoPlant.getTubers();
        System.out.println("Инвентарь: " + skuperfield.getInventory());
        
        // Скуперфильд снова шагает и проклинает
        System.out.println("\nШагать по рыхлой земле... было очень утомительно. Скуперфильд на все лады проклинал коротышек...");
        try {
            skuperfield.move(ground);
            System.out.println("Настроение после движения: " + skuperfield.getMood());
        } catch (InvalidActionException e) {
            System.out.println("Ошибка при движении: " + e.getMessage());
        }
        
        skuperfield.curse("Коротышки виноваты!");
        System.out.println("Настроение после проклятия: " + skuperfield.getMood());
        
        System.out.println("\n=== Конец истории ===");
    }
}
