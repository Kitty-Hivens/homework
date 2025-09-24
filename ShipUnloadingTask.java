package oop.multithreadingex;

import java.util.concurrent.Semaphore;

public record ShipUnloadingTask(int shipId, Semaphore docks) implements Runnable {
    private final static int numberOfBoxes = 10;
    private final static int unloadTimeMillis = 500;

    /**
     * TODO: write documentation
     */
    @Override
    public void run() {
        System.out.printf("The %d ship arrived at the port and is waiting for a free port.%n", shipId);
        try {
            docks.acquire();
            System.out.printf("The %d ship has entered port and is awaiting unloading.%n", shipId);

            for (int i = 1; i <= numberOfBoxes; i++) {
                Thread.sleep(unloadTimeMillis);
                System.out.printf("The %d ship unloaded the %d crate.%n", shipId, i);
            }

        } catch (InterruptedException e) {
            System.out.println("Interrupted" + e);
        } finally {
            docks.release();
        }
    }
}
