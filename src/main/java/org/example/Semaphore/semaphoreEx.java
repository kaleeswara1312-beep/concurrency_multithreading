package org.example.Semaphore;

import java.util.concurrent.Semaphore;

class FileProcessor {

    private final Semaphore semaphore = new Semaphore(3);

    void processFile(int fileNumber) {

        try {
            // Get permission
            semaphore.acquire();

            System.out.println(
                    Thread.currentThread().getName()
                            + " processing File " + fileNumber
            );

            // Simulate file processing
            Thread.sleep(3000);

            System.out.println(
                    Thread.currentThread().getName()
                            + " finished File " + fileNumber
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        } finally {
            // Give permission back
            semaphore.release();
        }
    }
}

public class semaphoreEx {

    public static void main(String[] args) {

        FileProcessor processor = new FileProcessor();

        for (int i = 1; i <= 10; i++) {

            int fileNumber = i;

            new Thread(() -> {
                processor.processFile(fileNumber);
            }, "Thread-" + i).start();
        }
    }
}
