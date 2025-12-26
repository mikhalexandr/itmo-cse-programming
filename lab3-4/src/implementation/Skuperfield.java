package implementation;

import java.util.Random;

import base_classes.Character;
import data_types.PlantColor;
import data_types.Location;
import data_types.Mood;
import data_types.Taste;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.PlantNotFoundException;
import interfaces.Edible;

public class Skuperfield extends Character {
    private boolean hasPotatoMisconception;
    
    public Skuperfield(Location startLoc) {
        super("Скуперфильд", startLoc);
        this.hasPotatoMisconception = true;
    }
    
    @Override
    public void move(Ground ground) {
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
                    String subText = String.format(" ,усаженной какими-то %s %s кустиками, достигавшими ему до колен",
                            colorToText, isBrittleStr);
                    text += subText;
                }
                case TIRED  -> {
                    this.setMood(Mood.ANGRY);
                    text = "Шагать по рыхлой земле, беспрерывно путаясь ногами в картофельной ботве, было очень утомительно";
                    text += this.curse(ground);
                }
                case ANGRY -> text = "Скуперфильд, уставший от шагания по рыхлой земле, начал ругаться матом";
                default -> text = "Скуперфильд шагал во сне";
            }
        } else {
            this.setMood(Mood.NORMAL);
            text = "Скуперфильд легко шагал по мягкой земле";
        }

        System.out.println(text);

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
    }

    private String curse(Ground ground) {
        String text = " Скуперфильд на все лады проклинал коротышек, вздумавших, словно ему назло, взрыхлить вокруг землю";
        if (ground.getPlantCount() > 0) {
            text += " и насадить на его пути все эти кустики";
        }
        return text;
    }
    
    public void uprootPlant(Ground ground) throws InvalidActionException, PlantNotFoundException {
        PotatoPlant plant = (PotatoPlant) ground.getPlantAt(currentLocation);
        if (plant.isUprooted()) {
            throw new InvalidActionException("Кустик уже вырван!");
        }
        plant.uproot();
        String text = "Выдернув из земли один кустик";
        System.out.println(text);
    }
    
    public void realizePotatoGrowInGround() {
        this.hasPotatoMisconception = false;
    }
    
    public boolean hasPotatoMisconception() {
        return hasPotatoMisconception;
    }
    
    public void tryToEat(Edible edible) throws DisgustingTasteException {
        Taste taste = edible.getTaste(false);
        if (taste == Taste.DISGUSTING) {
            throw new DisgustingTasteException("Этот вкус ужасен! Нельзя же есть это сырым!");
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + " (hasPotatoMisconception: " + hasPotatoMisconception + ")";
    }
}
