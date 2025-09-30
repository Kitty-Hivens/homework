package oop.mapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ElementCounter {

    /**
     * Подсчитывает количество повторений каждого элемента в массиве.
     * @param array Массив элементов
     * @return HashMap, где ключ — элемент, а значение — количество его повторений.
     */
    public static <T> Map<T, Integer> countFrequencies(T[] array) {
        // Создаем HashMap для хранения частоты (Element -> Count)
        Map<T, Integer> counts = new HashMap<>();

        // 1. Итерация по каждому элементу массива
        for (T element : array) {
            
            // 2. Использование метода getOrDefault для лаконичного подсчета
            // Если элемент уже есть, получаем его текущий счетчик и прибавляем 1.
            // Если элемента нет (default), используем 0 и прибавляем 1.
            int currentCount = counts.getOrDefault(element, 0);
            counts.put(element, currentCount + 1);
            
            /* Альтернативный, более длинный вариант:
            if (counts.containsKey(element)) {
                counts.put(element, counts.get(element) + 1);
            } else {
                counts.put(element, 1);
            }
            */
        }

        return counts;
    }

    public static void main(String[] args) {
        // Пример 1: Массив целых чисел
        Integer[] numbers = {1, 5, 2, 1, 3, 5, 5, 2, 8, 1};
        Map<Integer, Integer> numberFrequencies = countFrequencies(numbers);
        
        System.out.println("Массив: " + Arrays.toString(numbers));
        System.out.println("Частота чисел: " + numberFrequencies);
        // Ожидаемый результат: {1=3, 2=2, 3=1, 5=3, 8=1}

        System.out.println("---");
        
        // Пример 2: Массив строк
        String[] words = {"apple", "banana", "Apple", "cherry", "banana"};
        Map<String, Integer> wordFrequencies = countFrequencies(words);
        
        System.out.println("Массив: " + Arrays.toString(words));
        // Обратите внимание: "apple" и "Apple" считаются разными ключами, 
        // так как HashMap чувствителен к регистру.
        System.out.println("Частота слов: " + wordFrequencies); 
        // Ожидаемый результат: {apple=1, banana=2, Apple=1, cherry=1}
    }
}