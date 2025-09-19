package oop.multithreading;

public class ArraySum {

    public static long singleThreadSum(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] largeArray = new int[100_000_000]; // Большой массив для тестирования
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 100; // Заполняем какими-либо значениями
        }

        long startTime = System.nanoTime();
        long sum = singleThreadSum(largeArray);
        long endTime = System.nanoTime();
        System.out.println("Однопоточная сумма: " + sum);
        System.out.println("Время однопоточного подсчёта: " + (endTime - startTime) / 1_000_000.0 + " мс");
    }
}
