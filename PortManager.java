package oop.multithreadingex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PortManager {
    public static void main(String[] args) {
        Semaphore docks = new Semaphore(2);
        System.out.println("The port is ready to receive ships");

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 3; i++) {
                executorService.submit(new ShipUnloadingTask(i, docks));
            }

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("Interrupted" + e);
            Thread.currentThread().interrupt();
        }

        System.out.println("All ships have been unloaded and port operations are complete.");
    }
}
