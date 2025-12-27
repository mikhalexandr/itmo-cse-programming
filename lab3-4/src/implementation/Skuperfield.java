package implementation;

import base_classes.Character;
import base_classes.Item;
import base_classes.Plant;
import data_types.Location;
import data_types.Mood;
import data_types.PlantColor;
import data_types.Taste;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Skuperfield extends Character {
    private boolean hasPotatoUncertainty;
    
    public Skuperfield(Location startLoc) {
        super("Скуперфильд", startLoc);
        this.hasPotatoUncertainty = false;
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
        String text = "Скуперфильд на все лады проклинал коротышек, вздумавших, словно ему назло, взрыхлить вокруг землю";
        if (ground.getPlantCount() > 0) {
            text += " и насадить на его пути все эти кустики. ";
        }
        return text;
    }
    
    public Plant uprootPlant(Ground ground) throws InvalidActionException, PlantNotFoundException {
        PotatoPlant plant = (PotatoPlant) ground.getPlantAt(currentLocation);
        if (plant.isUprooted()) {
            throw new InvalidActionException("Кустик уже вырван!");
        }
        plant.uproot();
        String text = "Выдернув из земли один кустик";
        if (plant.hasFruit()) {
            text += ", он увидел несколько прицепившихся к корням желтоватых клубней. ";
        } else {
            text += ", он ничего не обнаружил. ";
        }
        System.out.println(text);
        return plant;
    }

    public Item examine(Plant plant) throws PlantNotFoundException {
        PotatoTuber tuber = null;
        if (plant instanceof PotatoPlant) {
            List<PotatoTuber> tubers = plant.getTubers();
            if (tubers.isEmpty()) {
                System.out.println("Осмотрев внимательно, Скуперфильд ничего не обнаружил.");
            }
            tuber = tubers.get(0);
            System.out.printf(
                    "Осмотрев клубни внимательно, Скуперфильд начал догадываться, что перед ним самый обыкновенный %s.",
                    tuber.getName()
            );
        } else {
            System.out.printf(
                    "Осмотрев растение внимательно, Скуперфильд начал догадываться, что перед ним самый обыкновенный %s. %n",
                    plant.getName()
            );
        }
        return tuber;
    }
    
    public void realizePotatoGrowInGround(PotatoTuber tuber) throws PlantNotFoundException {
        this.hasPotatoUncertainty = true;

        String tuberName = tuber.getName();

        System.out.printf(
                "Впрочем, он не был уверен в своей догадке, так как до этого видел %s только в жареном или вареном " +
                "виде и к тому же почему-то воображал, что картофель растёт на деревьях. ",
                tuberName
        );
    }
    
    public void tryToEat(PotatoTuber tuber) throws PlantNotFoundException, DisgustingTasteException {
        String text = "";
        
        if (!tuber.isClean()) {
            tuber.clean();
            text += "Отряхнув от земли один клубень, ";
        }

        text += "Скуперфильд откусил кусочек и попробовал его разжевать. ";

        Taste taste = tuber.getTaste();
        if (taste == Taste.DISGUSTING) {
            throw new DisgustingTasteException(text + "Сырой картофель показался ему страшно невкусным, даже противным. ");
        }
        
        System.out.println(text + "Картошечка показалась ему вкусной. ");
    }

    public void putInPocket(PotatoPlant plant, int amount) throws PlantNotFoundException, InventoryFullException {
        List<PotatoTuber> tubers = plant.getTubers();
        if (tubers.isEmpty()) {
            throw new PlantNotFoundException("Нет клубней для сбора!");
        }
        
        int count = Math.min(amount, tubers.size());
        int collected = 0;
        
        for (int i = 0; i < count; i++) {
            try {
                PotatoTuber tuber = tubers.get(i);
                this.getInventory().addItem(tuber);
                plant.removeTuber(tuber);
                collected++;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        
        System.out.printf("Сообразив, однако, что никто не стал бы выращивать совершенно бесполезных плодов, " +
                "он сунул вытащенные из земли %d картофелин в карман пиджака и отправился дальше. ",
                collected
        );
    }
    
    @Override
    public String toString() {
        return super.toString() + " (hasPotatoMisconception: " + hasPotatoUncertainty + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skuperfield that = (Skuperfield) o;
        return hasPotatoUncertainty == that.hasPotatoUncertainty;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasPotatoUncertainty);
    }
}
