package itmo.cse.lab5.server.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import itmo.cse.lab5.common.exceptions.FileReadException;
import itmo.cse.lab5.common.exceptions.FileWriteException;
import itmo.cse.lab5.common.exceptions.ValidationException;
import itmo.cse.lab5.server.models.SpaceMarine;

import java.io.*;
import java.util.LinkedList;

public class FileManager {
    private final String filePath;
    private final Gson gson;

    public FileManager(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public LinkedList<SpaceMarine> load() {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            SpaceMarine[] array = gson.fromJson(reader, SpaceMarine[].class);
            if (array == null) {
                return new LinkedList<>();
            }
            LinkedList<SpaceMarine> result = new LinkedList<>();
            for (SpaceMarine marine : array) {
                try {
                    marine.validate();
                    result.add(marine);
                } catch (ValidationException e) {
                    System.err.printf("Невалидный объект пропущен: %s%n\n", e.getMessage());
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new FileReadException(String.format("Файл не найден: %s", filePath), e);
        } catch (JsonSyntaxException e) {
            throw new FileReadException(String.format("Ошибка парсинга JSON: %s", filePath), e);
        } catch (IOException e) {
            throw new FileReadException(String.format("Ошибка чтения файла: %s", filePath), e);
        }
    }

    public void save(LinkedList<SpaceMarine> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(gson.toJson(collection));
        } catch (IOException e) {
            throw new FileWriteException(String.format("Ошибка записи в файл: %s", filePath), e);
        }
    }
}
