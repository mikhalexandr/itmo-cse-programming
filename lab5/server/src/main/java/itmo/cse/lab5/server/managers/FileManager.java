package itmo.cse.lab5.server.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import itmo.cse.lab5.common.exceptions.FileReadException;
import itmo.cse.lab5.common.exceptions.FileWriteException;
import itmo.cse.lab5.common.exceptions.ValidationException;
import itmo.cse.lab5.server.models.SpaceMarine;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Выполняет загрузку и сохранение коллекции в JSON-файл.
 */
public class FileManager {
    private final String filePath;
    private final Gson gson;

    /**
     * @param filePath путь к файлу коллекции
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().setDateFormat("HH:mm:ss dd.MM.yyyy").create();
    }

    /**
     * Загружает коллекцию из файла, пропуская невалидные элементы.
     *
     * @return загруженная коллекция
     * @throws FileReadException если файл не найден или не удалось прочитать/распарсить
     */
    public LinkedList<SpaceMarine> load() {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            SpaceMarine[] array = gson.fromJson(reader, SpaceMarine[].class);
            if (array == null) {
                return new LinkedList<>();
            }
            LinkedList<SpaceMarine> result = new LinkedList<>();
            Set<Integer> usedIds = new HashSet<>();
            for (SpaceMarine marine : array) {
                try {
                    marine.validate();
                    if (!usedIds.add(marine.getId())) {
                        System.err.printf("Невалидный объект пропущен: дубликат id=%d%n", marine.getId());
                        continue;
                    }
                    result.add(marine);
                } catch (ValidationException e) {
                    System.err.printf("Невалидный объект пропущен: %s%n", e.getMessage());
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new FileReadException(String.format("файл не найден [%s]", filePath), e);
        } catch (JsonSyntaxException e) {
            throw new FileReadException(String.format("ошибка парсинга JSON [%s]", filePath), e);
        } catch (IOException e) {
            throw new FileReadException(String.format("ошибка чтения файла [%s]", filePath), e);
        }
    }

    /**
     * Сохраняет коллекцию в файл в формате JSON.
     *
     * @param collection коллекция для сохранения
     * @throws FileWriteException если запись в файл не удалась
     */
    public void save(LinkedList<SpaceMarine> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(gson.toJson(collection));
        } catch (IOException e) {
            throw new FileWriteException(String.format("ошибка записи в файл [%s]", filePath), e);
        }
    }
}
