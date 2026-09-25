package org.example.CompleteableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FutureExample {

    public void test() throws Exception {

        // Create thread pool
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        // Submit asynchronous task
        Future<String> future = executor.submit(() -> {

            System.out.println(
                    "Task running in: "
                            + Thread.currentThread().getName()
            );

            Thread.sleep(3000);

            return "User data";
        });

        // Main thread continues
        System.out.println("Main thread is doing other work...");

        // Get result
        // This will wait if task is not completed
        String result = future.get();

        System.out.println("Result: " + result);

        // Shutdown executor
        executor.shutdown();
    }
}

class CompletableFutureExample {

    public void test() {

        // Submit task to another thread
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {

                    System.out.println(
                            "Running in: "
                                    + Thread.currentThread().getName()
                    );

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    return "User Data";
                });

        // Main thread continues
        System.out.println("Main thread is doing other work...");

        // Execute when result is available
        future.thenAccept(result -> {
            System.out.println("Result: " + result);
        });

        // Only for this simple main() demonstration
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class CompleteableFutureDemo {
    public static void main(String[] args) throws Exception {
//        FutureExample futureExample = new FutureExample();
//        futureExample.test();

        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        completableFutureExample.test();
    }
}
