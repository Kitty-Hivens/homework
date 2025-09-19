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
        int[] largeArray = new int[100_000_000]; // Великий масив для тестування
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 100; // Заповнюємо якимись значеннями
        }

        long startTime = System.nanoTime();
        long sum = singleThreadSum(largeArray);
        long endTime = System.nanoTime();
        System.out.println("Однопоточна сума: " + sum);
        System.out.println("Час однопоточного підрахунку: " + (endTime - startTime) / 1_000_000.0 + " мс");
    }
}
