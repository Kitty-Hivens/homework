package oop.mapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DictionaryManager {

    private static final String DICT_FILE = "dictionary.dat";

    /**
     * Загружает словарь из текстового файла.
     */
    public static HashMap<String, String> loadDictionary() {
        var dictionary = new HashMap<String, String>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DICT_FILE));

            for (String line : lines) {
                // Ищем первое вхождение знака равенства для разделения
                int separatorIndex = line.indexOf('=');
                if (separatorIndex > 0) {
                    String english = line.substring(0, separatorIndex).trim().toLowerCase();
                    String ukrainian = line.substring(separatorIndex + 1).trim().toLowerCase();
                    if (!english.isEmpty() && !ukrainian.isEmpty()) {
                        dictionary.put(english, ukrainian);
                    }
                }
            }
            System.out.println("Словарь загружен. Записей: " + dictionary.size());
        } catch (IOException e) {
            // Файл не найден или ошибка чтения. Создаем пустой словарь.
            System.out.println("Файл словаря " + DICT_FILE + " не найден. Создан пустой словарь.");
        }
        return dictionary;
    }

    /**
     * Сохраняет словарь в текстовый файл.
     */
    public static void saveDictionary(HashMap<String, String> dictionary) {
        var stringBuilder = new StringBuilder();

        // Проходим по всем парам и форматируем их в "ключ=значение\n"
        for (var entry : dictionary.entrySet()) {
            stringBuilder
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(System.lineSeparator()); // Используем системный разделитель строк
        }

        try {
            Files.writeString(Paths.get(DICT_FILE), stringBuilder.toString());
            System.out.println("Словарь успешно сохранен в файл: " + DICT_FILE);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении словаря: " + e.getMessage());
        }
    }

    /**
     * Позволяет пользователю вручную наполнить словарь.
     */
    public static void manualFillDictionary(HashMap<String, String> dictionary, Scanner scanner) {
        System.out.println("\n--- РЕЖИМ НАПОЛНЕНИЯ СЛОВАРЯ ---");
        System.out.println("Введите 'стоп' или 'exit' для завершения.");

        while (true) {
            System.out.print("Введите АНГЛИЙСКОЕ слово: ");
            String english = scanner.nextLine().trim();

            if (english.equalsIgnoreCase("стоп") || english.equalsIgnoreCase("exit")) {
                break;
            }

            if (english.isEmpty()) {
                System.out.println("Слово не может быть пустым.");
                continue;
            }

            System.out.print("Введите УКРАИНСКИЙ перевод для '" + english + "': ");
            String ukrainian = scanner.nextLine().trim();

            if (ukrainian.isEmpty()) {
                System.out.println("Перевод не может быть пустым.");
                continue;
            }

            // Ключи всегда храним в нижнем регистре
            dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
            System.out.println(">> Добавлено: " + english.toLowerCase() + " = " + ukrainian.toLowerCase());
        }
        System.out.println("--- РЕЖИМ НАПОЛНЕНИЯ ЗАВЕРШЕН ---");
    }
}