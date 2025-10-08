package oop.stream.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TaskSeven {
    /**
     * Пользовательская реализация Collectors.toSet()
     */
    public static <T> Collector<T, Set<T>, Set<T>> toCustomSet() {

        // 1. Supplier: Создает новый HashSet
        Supplier<Set<T>> supplier = HashSet::new;

        // 2. Accumulator: Добавляет элемент в Set
        BiConsumer<Set<T>, T> accumulator = Set::add;

        // 3. Combiner: Объединяет два Set (для parallelStream)
        BinaryOperator<Set<T>> combiner = (left, right) -> {
            left.addAll(right);
            return left;
        };

        // 4. Finisher: Нет необходимости в трансформации, возвращаем идентичность
        Function<Set<T>, Set<T>> finisher = Function.identity();

        // Возвращаем Collector, указывая характеристики (IDENTITY_FINISH, UNORDERED)
        return Collector.of(supplier, accumulator, combiner, finisher,
                Collector.Characteristics.IDENTITY_FINISH,
                Collector.Characteristics.UNORDERED);
    }

    // Вспомогательный метод для проверки наличия гласных (a, e, i, o, u)
    private static boolean containsVowel(String word) {
        String cleanWord = word.toLowerCase().replaceAll("[^a-z]", "");
        return cleanWord.matches(".*[aeiou].*");
    }

    /**
     * Собирает в Map<Слово, Длина> только те слова, в которых есть гласные.
     */
    public static Map<String, Integer> collectWordsWithVowels(String text) {
        return Arrays.stream(text.split("\\s+"))
                // 1. Фильтрация: Оставляем только слова, содержащие гласные
                .filter(TaskSeven::containsVowel)

                // 2. Сбор в Map:
                //    - Ключ (Key Mapper): Само слово (Function.identity())
                //    - Значение (Value Mapper): Длина слова (String::length)
                //    - Merge Function: В случае дубликатов, используем длину первого (для примера)
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length,
                        (existing, _) -> existing
                ));
    }
}
