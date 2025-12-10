package implementation;

import base_classes.Character;
import base_classes.Plant;
import data_types.Location;
import data_types.Mood;
import data_types.Taste;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import interfaces.Edible;

public class Skuperfield extends Character {
    private boolean hasPotatoMisconception;
    
    public Skuperfield(Location startLoc) {
        super("Скуперфильд", startLoc);
        this.hasPotatoMisconception = true;
    }
    
    @Override
    public void move(Ground ground) {
        if (ground.isDifficultToMove()) {
            if (this.getMood() == Mood.NORMAL) {
                this.setMood(Mood.TIRED);

            } else if (this.getMood() == Mood.TIRED) {
                this.setMood(Mood.ANGRY);
            }
        } else {
            this.setMood(Mood.NORMAL);
        }
        this.setLocation(new Location(currentLocation.x() + 1, currentLocation.y()));
    }
    
    public void uprootPlant(Ground ground) throws InvalidActionException {
        Plant plant = ground.getPlantAt(currentLocation);
        if (plant.isUprooted()) {
            throw new InvalidActionException("Клубень уже вырван!");
        }
        plant.uproot();
    }
    
    public void realizePotatoGrowInGround() {
        this.hasPotatoMisconception = false;
    }
    
    public void curse(String reason) {
        this.setMood(Mood.ANGRY);
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
