package itmo.cse.lab5.server.input;

import itmo.cse.lab5.common.util.Validator;
import itmo.cse.lab5.server.models.*;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public SpaceMarine readSpaceMarine() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        float health = readHealth();
        long height = readHeight();
        AstartesCategory category = readCategory();
        MeleeWeapon meleeWeapon = readMeleeWeapon();
        Chapter chapter = readChapter();
        return new SpaceMarine(
                name, coordinates, health, height, category, meleeWeapon,  chapter
        );
    }

    private String readName() {
        while (true) {
            System.out.print("Введите имя: ");
            String input = scanner.nextLine().trim();
            if (Validator.isValidString(input)) {
                return input;
            }
            System.out.println("Ошибка: имя не может быть пустым.");
        }
    }

    private Coordinates readCoordinates() {
        double x = readDouble();
        int y = readInt();
        return new Coordinates(x, y);
    }

    private double readDouble() {
        while (true) {
            System.out.print("Введите координату x: ");
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private int readInt() {
        while (true) {
            System.out.print("Введите координату y: ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private float readHealth() {
        while (true) {
            System.out.print("Введите здоровье (> 0): ");
            try {
                float health =  Float.parseFloat(scanner.nextLine().trim());
                if (Validator.isGreaterThan(health, 0)) {
                    return health;
                }
                System.out.println("Ошибка: здоровье должно быть больше 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private long readHeight() {
        while (true) {
            System.out.print("Введите рост: ");
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private AstartesCategory readCategory() {
        AstartesCategory[] values = AstartesCategory.values();
        while (true) {
            System.out.println("Выберите категорию:");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("  %d - %s%n", i + 1, values[i]);
            }
            System.out.print("Введите номер: ");
            try {
                int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (index >= 0 && index < values.length) {
                    return values[index];
                }
                System.out.printf("Ошибка: введите номер от 1 до %d.%n", values.length);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private MeleeWeapon readMeleeWeapon() {
        MeleeWeapon[] values = MeleeWeapon.values();
        while (true) {
            System.out.println("Выберите оружие ближнего боя (Enter - пропустить):");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("  %d - %s%n", i + 1, values[i]);
            }
            System.out.print("Выберите номер: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < values.length) {
                    return values[index];
                }
                System.out.printf("Ошибка: введите номер от 1 до %d.%n", values.length);
            }  catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private Chapter readChapter() {
        System.out.print("Введите имя ордена (Enter - пропустить): ");
        String name =  scanner.nextLine().trim();
        if (name.isEmpty()) {
            return null;
        }

        System.out.print("Введите родительский легион (Enter - пропустить): ");
        String legion = scanner.nextLine().trim();
        return new Chapter(name, legion.isEmpty() ? null : legion);
    }
}
