package tetris;

import java.io.*;
import java.util.*;

/**
 * Класс для управления таблицей рекордов для игры Тетрис.
 * Предоставляет методы для добавления новых рекордов, получения существующих записей и сохранения их в файл.
 */
public class RecordManager {
    private final String fileName;

    /**
     * Создает экземпляр RecordManager с указанным именем файла.
     *
     * @param fileName имя файла для хранения рекордов
     */
    public RecordManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Добавляет новую запись для указанного игрока с заданным количеством очков.
     * Если у игрока уже есть запись, новый результат заменяет старый.
     * Рекорды сортируются по убыванию количества очков, и сохраняются только три лучших.
     *
     * @param playerName имя игрока
     * @param score      набранные очки
     */
    public void addRecord(String playerName, int score) {
        Map<String, Integer> records = loadRecords();
        records.put(playerName, score);

        // Сортировка записей по убыванию количества очков
        List<Map.Entry<String, Integer>> sortedRecords = new ArrayList<>(records.entrySet());
        sortedRecords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Оставляем только топ-3 записи
        if (sortedRecords.size() > 3) {
            sortedRecords = sortedRecords.subList(0, 3);
        }

        // Преобразование обратно в карту
        records = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedRecords) {
            records.put(entry.getKey(), entry.getValue());
        }

        saveRecords(records);
    }

    /**
     * Возвращает текущие записи таблицы рекордов.
     *
     * @return карта, содержащая имена игроков и их результаты
     */
    public Map<String, Integer> getRecords() {
        return loadRecords();
    }

    /**
     * Загружает записи таблицы рекордов из файла.
     *
     * @return карта, содержащая имена игроков и их результаты
     */
    private Map<String, Integer> loadRecords() {
        Map<String, Integer> records = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    records.put(playerName, score);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * Сохраняет записи таблицы рекордов в файл.
     *
     * @param records карта, содержащая имена игроков и их результаты
     */
    private void saveRecords(Map<String, Integer> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : records.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
                System.out.println("Сохранен рекорд: " + entry.getKey() + " - " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
