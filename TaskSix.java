package oop.stream.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskSix {
    public static int sumLengthOfLongWords(String text) {
        return Arrays.stream(text.split("\\s+"))
                // 1. Фильтрация: оставляем слова, длина которых > 4
                .filter(word -> word.replaceAll("[^a-zA-Z]", "").length() > 4)

                // 2. Reduce:
                //    - Начальное значение: 0 (для суммы)
                //    - Аккумулятор: суммирует длины слов
                .reduce(0,
                        (totalLength, word) -> totalLength + word.replaceAll("[^a-zA-Z]", "").length(),
                        Integer::sum); // Комбайнер (используется при parallelStream)
    }

    public static Optional<Integer> productOfIntegers(List<Integer> numbers) {
        // Используем 1-аргументную форму reduce, которая возвращает Optional<Integer>
        return numbers.stream()
                .reduce((a, b) -> a * b); // Аккумулятор: умножает текущий результат (a) на следующий элемент (b)
    }

    public static int productOfIntegersWithIdentity(List<Integer> numbers) {
        // Используем 3-аргументную форму reduce, где identity = 1 (нейтральный элемент для умножения)
        return numbers.stream()
                .reduce(1,
                        (a, b) -> a * b,  // Аккумулятор
                        (a, b) -> a * b); // Комбайнер
    }

    public static Optional<Integer> findMaxUsingReduce(List<Integer> numbers) {
        // Max(a, b) = a > b ? a : b
        return numbers.stream()
                .reduce((a, b) -> a > b ? a : b);
    }

    // Аналогично можно заменить min:
    public static Optional<Integer> findMinUsingReduce(List<Integer> numbers) {
        // Min(a, b) = a < b ? a : b
        return numbers.stream()
                .reduce((a, b) -> a < b ? a : b);
    }

}
