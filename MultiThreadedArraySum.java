package oop.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadedArraySum {

    private record SumTask(int[] array, int start, int end) implements Callable<Long> {

        @Override
            public Long call() {
                long partialSum = 0;
                for (int i = start; i < end; i++) {
                    partialSum += array[i];
                }
                return partialSum;
            }
        }

    public static long multiThreadSum(int[] arr, int numThreads) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> futures = new ArrayList<>();

        int chunkSize = arr.length / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? arr.length : (i + 1) * chunkSize;
            SumTask task = new SumTask(arr, start, end);
            futures.add(executor.submit(task));
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // Ждём завершения всех потоков
        return totalSum;
    }

    public static void main(String[] args) throws Exception {
        int[] largeArray = new int[100_000_000]; // Великий масив
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i % 100;
        }

        int numThreads = Runtime.getRuntime().availableProcessors(); // Используем количество ядер процессора
        System.out.println("Использовано потоков: " + numThreads);

        long startTime = System.nanoTime();
        long sum = multiThreadSum(largeArray, numThreads);
        long endTime = System.nanoTime();
        System.out.println("Многопоточная сумма: " + sum);
        System.out.println("Время многопоточного подсчёта: " + (endTime - startTime) / 1_000_000.0 + " мс");

        System.out.println("\n--- Сравнение ---");
        long startTimeSingle = System.nanoTime();
        long sumSingle = ArraySum.singleThreadSum(largeArray); // Використовуємо метод з попереднього класу
        long endTimeSingle = System.nanoTime();
        System.out.println("Однопоточная сума (для сравнения): " + sumSingle);
        System.out.println("Время однопоточного подсчёта (для сравнения): " + (endTimeSingle - startTimeSingle) / 1_000_000.0 + " мс");
    }
}
