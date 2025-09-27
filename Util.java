package oop.generics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Util {
    public static void createAndDelete() {
        List<Integer> numbers = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        numbers.removeFirst();
        numbers.removeFirst();
        numbers.removeLast();

        numbers.forEach(System.out::println);
    }
    /**
     * Основной метод для выполнения анализа частоты букв в файле.
     * @param filename Имя файла для анализа.
     */
    public static void analyzeFileFrequency(String filename) {
        Map<Character, Integer> counts = new HashMap<>();
        long totalLetters;

        try {
            totalLetters = readAndCount(filename, counts);

            if (totalLetters == 0) {
                System.out.println("Файл пуст или не содержит английских букв.");
                return;
            }

            printSortedFrequencies(counts, totalLetters);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Читает файл, подсчитывает количество каждой английской буквы (без учета регистра).
     * @param filename Имя файла.
     * @param counts Карта для хранения подсчетов (передается для заполнения).
     * @return Общее количество обработанных английских букв.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    private static long readAndCount(String filename, Map<Character, Integer> counts) throws IOException {
        long totalLetters = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                char ch = (char) charCode;
                if (Character.isLetter(ch)) {
                    char upperCaseCh = Character.toUpperCase(ch);
                    counts.put(upperCaseCh, counts.getOrDefault(upperCaseCh, 0) + 1);
                    totalLetters++;
                }
            }
        }
        return totalLetters;
    }

    /**
     * Вычисляет относительную частоту и выводит отсортированный результат.
     * @param counts Карта с подсчетами букв.
     * @param totalLetters Общее количество букв.
     */
    private static void printSortedFrequencies(Map<Character, Integer> counts, long totalLetters) {
        System.out.println("\n== Анализ частоты букв (по убыванию) ==");

        List<Map.Entry<Character, Integer>> sortedEntries = counts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            char letter = entry.getKey();
            int count = entry.getValue();
            double frequency = (double) count / totalLetters;

            // Вывод в формате "A: 8.167%"
            System.out.printf("%c: %.3f%%%n", letter, frequency * 100);
        }
    }

    /**
     * Симулирует процесс раздваивания в очереди "Двойной Колы".
     *
     * @param initialNames Исходный список имен в начале очереди.
     * @param nThendIssued Общее количество выданных стаканов колы.
     * @return Очередь (Queue) с именами после выдачи nThendIssued стаканов.
     */
    public static Queue<String> simulateDoubleCola(String[] initialNames, int nThendIssued) {
        Queue<String> colaQueue = new LinkedList<>(Arrays.asList(initialNames));

        for (int i = 0; i < nThendIssued; i++) {
            String person = colaQueue.poll();

            if (person == null) {
                break;
            }

            colaQueue.add(person);
            colaQueue.add(person);
        }

        return colaQueue;
    }
}
