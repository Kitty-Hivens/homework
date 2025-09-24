package oop.multithreadingex;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedFileSearch {

    private static final int THREAD_COUNT = workerCount();
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private final AtomicInteger activeTasks = new AtomicInteger(0);
    private final CountDownLatch latch = new CountDownLatch(1);

    private static int workerCount() {
        int cpus = Runtime.getRuntime().availableProcessors() - 1;
        return Math.max(1, cpus);
    }

    public static void main(String[] args) {
        String fileName = "test.txt"; // Имя файла для поиска
        String rootDirectory = "/home/"; // Начальная директория для поиска

        if (!Files.isDirectory(Paths.get(rootDirectory))) {
            System.err.println("The specified path is not a directory.");
            return;
        }

        System.out.println("Start searching for a file '" + fileName + "' in '" + rootDirectory + "'...");
        new MultiThreadedFileSearch().findFile(fileName, rootDirectory);
    }

    public void findFile(String fileName, String rootDirectory) {
        Path rootPath = Paths.get(rootDirectory);
        submitSearchTask(rootPath, fileName);
        
        try {
            // Ждем завершения первой задачи
            latch.await();
            // Ждем, пока все активные задачи не завершатся
            while (activeTasks.get() > 0) {
                Thread.sleep(100); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
            System.out.println("Search completed.");
        }
    }

    private void submitSearchTask(Path directory, String fileName) {
        activeTasks.incrementAndGet();
        executor.submit(() -> {
            try {
                searchInDirectory(directory, fileName);
            } finally {
                if (activeTasks.decrementAndGet() == 0) {
                    latch.countDown();
                }
            }
        });
    }

    private void searchInDirectory(Path directory, String fileName) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    submitSearchTask(entry, fileName);
                } else if (entry.getFileName().toString().equals(fileName)) {
                    System.out.println("File found: " + entry.toAbsolutePath());
                }
            }
        } catch (IOException e) {
            // Игнорируем ошибки доступа, чтобы поиск не прерывался
            System.err.println("Unable to access: " + directory + " (" + e.getMessage() + ")");
        }
    }
}
