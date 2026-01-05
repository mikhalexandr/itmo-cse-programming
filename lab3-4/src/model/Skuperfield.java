package model;

import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;
import interfaces.Cleanable;
import interfaces.Edible;
import types.Location;
import types.Mood;
import types.PlantColor;
import types.Taste;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Skuperfield extends Character {
    private boolean hasItemUncertainty;

    public Skuperfield(Location startLoc) {
        super("Скуперфильд", startLoc);
        this.hasItemUncertainty = false;
    }

    @Override
    public void move(Ground ground) throws InvalidActionException {
        String text;
        if (ground.isDifficultToMove()) {
            switch (this.getMood()) {
                case NORMAL -> {
                    this.setMood(Mood.TIRED);
                    text = "Скуперфильд обнаружил, что шагает по рыхлой земле";
                    String colorStr = ground.getDominantColor();
                    String colorToText = "";
                    if (colorStr != null) {
                        colorToText = switch (PlantColor.valueOf(colorStr)) {
                            case DARK_GREEN -> "тёмно-зелёными";
                            case LIGHT_GREEN -> "светло-зелёными";
                            case YELLOW -> "жёлтыми";
                        };
                    }
                    boolean isBrittle = ground.hasMajorityBrittlePlants();
                    String isBrittleStr = "";
                    if  (isBrittle) {
                        isBrittleStr = ", ломкими";
                    }
                    String subText = String.format(", усаженной какими-то %s%s кустиками, достигавшими ему до колен. ",
                            colorToText, isBrittleStr);
                    text += subText;
                }
                case TIRED  -> {
                    this.setMood(Mood.ANGRY);
                    text = "Шагать по рыхлой земле, беспрерывно путаясь ногами в картофельной ботве, было очень утомительно. ";
                    text += this.curse(ground);
                }
                case ANGRY -> text = "Скуперфильд, уставший от шагания по рыхлой земле, начал ругаться матом. ";
                default -> text = "Скуперфильд шагал во сне. ";
            }
        } else {
            this.setMood(Mood.NORMAL);
            text = "Скуперфильд легко шагал по мягкой земле. ";
        }

        Random rnd = new Random();
        int caseNum = rnd.nextInt(3);
        int dx = 0;
        int dy = 0;
        switch (caseNum) {
            case 0 -> dx = 1;
            case 1 -> dy = 1;
            case 2 -> { dx = 1; dy = 1; }
        }
        this.setLocation(new Location(currentLocation.x() + dx, currentLocation.y() + dy));

        System.out.println(text);
    }

    private String curse(Ground ground) {
        String text = "\nСкуперфильд на все лады проклинал коротышек, вздумавших, словно ему назло, взрыхлить вокруг землю";
        if (ground.getPlantCount() > 0) {
            text += " и насадить на его пути все эти кустики. ";
        }
        return text;
    }

    public Plant uprootPlant(Ground ground) throws InvalidActionException, PlantNotFoundException {
        Plant plant = ground.getPlantAt(currentLocation);

        if (plant.isUprooted()) throw new InvalidActionException("Скуперфильд начал сходить с ума, " +
                "ведь начал вырывать уже вырванные растения!");
        plant.uproot();

        String text = "Выдернув из земли " + plant.getName();
        if (plant.hasFruit()) {
            text += ", он увидел что-то на корнях.";
        } else {
            text += ", он увидел его корни.";
        }
        System.out.println(text);

        return plant;
    }

    public Item examine(Plant plant) {
        System.out.printf("Скуперфильд внимательно осмотрел %s. ", plant.getName());

        Item item = plant.getSampleItem();
        if (item != null) {
            System.out.printf("Он начал догадываться, что перед ним самый обыкновенный %s.\n", item.getName());
            return item;
        } else {
            System.out.println("Плодов там не было, но корни были прикольные.");
        }

        return null;
    }

    public void realizeItemGrowInGround(Item item) {
        this.hasItemUncertainty = true;

        String itemName = item.getName();
        System.out.printf(
                "Впрочем, он далеко не был уверен в своей догадке, так как до этого видел %s только в жареном или вареном " +
                "виде и к тому же почему-то воображал, что %s растёт на деревьях.\n",
                itemName, itemName
        );
    }

    public void tryToEat(Item item) throws DisgustingTasteException {
        String itemName = item.getName();
        if (!(item instanceof Edible food)) {
            System.out.printf("Скуперфильд попытался укусить %s, но чуть не сломал зуб. Несъедобно.\n", itemName);
            return;
        }

        String text = "";
        if (item instanceof Cleanable cleanableItem) {
            if (!cleanableItem.isClean()) {
                cleanableItem.clean();
                text += "Отряхнув от земли " + itemName + ", ";
            }
        }
        text += "Скуперфильд откусил кусочек и попробовал его разжевать. ";
        if (food.getTaste() == Taste.DISGUSTING) {
            throw new DisgustingTasteException(text + "Какая гадость, сырой " + itemName + " ужасен на вкус.");
        }
        System.out.println(text + "Ммм, вполне съедобно.");
    }

    public void putInPocket(Item item) throws InventoryFullException {
        if (item == null) return;
        this.getInventory().addItem(item);
    }

    public void putInPocket(List<? extends Item> items) throws InventoryFullException {
        if (items == null || items.isEmpty()) {
            System.out.println("Класть нечего.");
            return;
        }

        int count = 0;
        InventoryFullException lastError = null;
        for (Item item : items) {
            try {
                this.putInPocket(item);
                count++;
            } catch (InventoryFullException e) {
                lastError = e;
                break;
            }
        }

        String itemName = items.get(0).getName();
        System.out.printf("Сообразив, что никто не стал бы выращивать совершенно бесполезных плодов, " +
                        "он сунул вытащенные из земли %d шт. [%s] в карман пиджака. ",
                count, itemName
        );

        if (lastError != null) {
            System.out.print("\nОстальное не влезло. ");
            throw lastError;
        } else {
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (hasPotatoMisconception: " + hasItemUncertainty + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skuperfield that = (Skuperfield) o;
        return hasItemUncertainty == that.hasItemUncertainty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasItemUncertainty);
    }
}
