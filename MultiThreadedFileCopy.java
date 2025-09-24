// MultiThreadedFileCopy.java
package oop.multithreadingex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadedFileCopy {
    private static final int CHUNK_SIZE = 1024 * 1024; // 1 MB
    private static final AtomicLong copiedBytes = new AtomicLong(0);

    private static int workerCount() {
        int cpus = Runtime.getRuntime().availableProcessors() - 1;
        return Math.max(1, cpus);
    }

    public void copy(Path source, Path dest) throws IOException, InterruptedException {
        if (!Files.exists(source)) throw new IOException("Source file not found: " + source);
        long fileSize = Files.size(source);
        copiedBytes.set(0);

        if (fileSize == 0) {
            Files.copy(source, dest, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Empty file copied.");
            return;
        }

        long numChunksLong = (fileSize + CHUNK_SIZE - 1) / CHUNK_SIZE; // ceil division
        if (numChunksLong > Integer.MAX_VALUE) throw new IOException("File too large to chunk.");
        int numChunks = (int) numChunksLong;

        CountDownLatch latch = new CountDownLatch(numChunks);

        System.out.printf("File size is %.2f MB%n", (double) fileSize / (1024 * 1024));
        System.out.printf("Copying %d chunks from %s to %s%n", numChunks, source, dest);

        // Ensure dest parent exists and pre-allocate file length
        if (dest.getParent() != null) Files.createDirectories(dest.getParent());
        try (RandomAccessFile raf = new RandomAccessFile(dest.toFile(), "rw")) {
            raf.setLength(fileSize);
        }

        Thread progressMonitor = startProgressMonitor(latch, fileSize);

        ExecutorService executor = Executors.newFixedThreadPool(workerCount());
        try {
            for (int i = 0; i < numChunks; i++) {
                long offset = (long) i * CHUNK_SIZE;
                int length = (int) Math.min(CHUNK_SIZE, fileSize - offset);
                executor.submit(new FileCopyTask(source, dest, offset, length, latch, copiedBytes));
            }

            // wait for all chunks
            latch.await();

            // give executor chance to finish (should be done)
            executor.shutdown();
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.err.println("Executor did not terminate in time; forcing shutdown.");
                executor.shutdownNow();
            }

            // final progress line
            System.out.printf("\rProgress is 100.00%% (%d/%d bytes)%n", fileSize, fileSize);
        } finally {
            progressMonitor.interrupt();
            if (!executor.isShutdown()) executor.shutdownNow();
            System.out.println("Copy finished!");
        }
    }

    private static Thread startProgressMonitor(CountDownLatch latch, long fileSize) {
        Thread t = new Thread(() -> {
            try {
                while (latch.getCount() > 0) {
                    long current = copiedBytes.get();
                    double progress = (double) current / fileSize * 100.0;
                    System.out.printf("\rProgress is %.2f%% (%d/%d bytes)", progress, current, fileSize);
                    System.out.flush();
                    Thread.sleep(500);
                }
            } catch (InterruptedException ignored) {
            }
        }, "progress-monitor");
        t.setDaemon(true);
        t.start();
        return t;
    }
}
