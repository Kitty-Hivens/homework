// FileCopyTask.java
package oop.multithreadingex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public record FileCopyTask(Path source, Path dest, long offset, int length, CountDownLatch latch, AtomicLong copiedBytes) implements Runnable {

    @Override
    public void run() {
        try (RandomAccessFile src = new RandomAccessFile(source.toFile(), "r");
             RandomAccessFile dst = new RandomAccessFile(dest.toFile(), "rw")) {

            byte[] buffer = new byte[length];
            src.seek(offset);
            src.readFully(buffer); // либо loop чтения, либо readFully
            dst.seek(offset);
            dst.write(buffer, 0, buffer.length);

            copiedBytes.addAndGet(buffer.length);
        } catch (IOException e) {
            System.err.printf("Chunk error: offset=%d length=%d -> %s%n", offset, length, e);
        } finally {
            latch.countDown();
        }
    }
}
