package oop.stream.util;

import oop.stream.entity.Cat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskTwo {
    static final String TAG_START = "<groupId>";
    static final String TAG_END = "</groupId>";

    public static String removeTrashFromString(String text) {
        return Arrays.stream(text.split("\\s+")) // 1. Разбиваем на слова
                .filter(word -> word.length() <= 5)            // 2. Фильтруем по длине
                .collect(Collectors.joining(" "));             // 3. Собираем обратно
    }

    public static String removeNotEnglishFromString(String text) {
        return text.chars() // 1. Преобразуем в поток int (кодов символов)
                .filter(c -> (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) // 2. Фильтруем: только латинские буквы
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) // 3. Собираем в строку
                .toString();
    }

    public static List<Cat> filterAndSortCats(List<Cat> cats) {

        return cats.stream()
                .filter(cat -> cat.getWeight() >= 3.0) // 1. Фильтрация (вес >= 3 кг)
                .sorted(Comparator.comparing(Cat::getName)) // 2. Сортировка по имени
                .collect(Collectors.toList()); // 3. Сбор в новый список
    }

    public static List<String> getDataFromXML(String[] xmlLines) {
        return Arrays.stream(xmlLines)
                .filter(line -> line.trim().startsWith(TAG_START)) // 2. Фильтруем строки с <groupId>
                .map(line -> line.trim()
                        .replace(TAG_START, "")
                        .replace(TAG_END, "")) // 3. Извлекаем значение (удаляем теги)
                .toList(); // 4. Сбор в список
    }
}
