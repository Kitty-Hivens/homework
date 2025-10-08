package oop.stream.util;

import oop.stream.entity.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskFour {
    // Вспомогательный метод для подсчета гласных (a, e, i, o, u)
    private static int countVowels(String word) {
        // Убираем знаки препинания и приводим к нижнему регистру для корректного подсчета
        String cleanWord = word.toLowerCase().replaceAll("[^a-z]", "");
        int count = 0;
        for (char c : cleanWord.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static List<String> sortWordsByVowels(String text) {
        return Arrays.stream(text.split("\\s+"))
                // 1. Фильтрация: исключаем слова, в которых нет гласных
                .filter(word -> countVowels(word) > 0)
                // 2. Сортировка: по возрастанию количества гласных
                .sorted(Comparator.comparing(TaskFour::countVowels))
                // 3. Сбор результата в список
                .collect(Collectors.toList());
    }

    public static List<Student> filterAndSortStudents(Student[] students) {
        return Arrays.stream(students)
                // 1. Фильтрация: возраст строго больше 20
                .filter(student -> student.getAge() > 20)
                // 2. Сортировка: по фамилии (лексикографически)
                .sorted(Comparator.comparing(Student::getLastName))
                // 3. Сбор в новый список
                .collect(Collectors.toList());
    }

    public static void processAndPrintNumbers(List<Integer> numbers) {
        numbers.stream()
                // 1. Фильтрация: оставляем только числа больше 10
                .filter(n -> n > 10)
                // 2. Сортировка: по значению последней цифры (n % 10)
                .sorted(Comparator.comparingInt(n -> n % 10))
                // 3. Вывод на экран
                .forEach(System.out::println);
    }

}