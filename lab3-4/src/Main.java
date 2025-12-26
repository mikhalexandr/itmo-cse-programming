import data_types.GroundType;
import data_types.Location;
import data_types.PlantColor;
import exceptions.DisgustingTasteException;
import exceptions.InvalidActionException;
import exceptions.InventoryFullException;
import exceptions.PlantNotFoundException;
import implementation.*;


public class Main {
    private static final int TEXT_WIDTH = 120;
    
    private static String formatText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        StringBuilder result = new StringBuilder();
        String[] paragraphs = text.split("\n", -1);
        
        for (int p = 0; p < paragraphs.length; p++) {
            String paragraph = paragraphs[p].trim();
            if (paragraph.isEmpty()) {
                if (p < paragraphs.length - 1) {
                    result.append("\n");
                }
                continue;
            }
            
            String[] words = paragraph.split("\\s+");
            StringBuilder currentLine = new StringBuilder();
            
            for (String word : words) {
                if (word.length() > Main.TEXT_WIDTH) {
                    if (!currentLine.isEmpty()) {
                        result.append(currentLine).append("\n");
                        currentLine = new StringBuilder();
                    }
                    int pos = 0;
                    while (pos < word.length()) {
                        int end = Math.min(pos + Main.TEXT_WIDTH, word.length());
                        result.append(word, pos, end);
                        if (end < word.length()) {
                            result.append("\n");
                        }
                        pos = end;
                    }
                } else if (currentLine.length() + word.length() + (!currentLine.isEmpty() ? 1 : 0) <= Main.TEXT_WIDTH) {
                    if (!currentLine.isEmpty()) {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                } else {
                    if (!currentLine.isEmpty()) {
                        result.append(currentLine).append("\n");
                    }
                    currentLine = new StringBuilder(word);
                }
            }
            
            if (!currentLine.isEmpty()) {
                result.append(currentLine);
            }
            
            if (p < paragraphs.length - 1) {
                result.append("\n");
            }
        }
        
        return result.toString();
    }
    
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
        
        System.out.print(formatText(output.toString()));
    }
}
