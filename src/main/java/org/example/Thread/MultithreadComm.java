package org.example.Thread;

class SharedThread {
        private int value;
        private boolean available = false;

        // Producer
        synchronized void produce(int value) {
            System.out.println("Producer entered "+ value);
            while (available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            this.value = value;
            available = true;

            System.out.println("Produced: " + value);

            notify();   // Wake up one waiting thread
        }

        // Consumer
        synchronized int consume() {

            System.out.println("consumer entered "+ value);

            while (!available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            int result = value;
            available = false;

            System.out.println("Consumed: " + result);

            notify();   // Wake up one waiting thread

            return result;
        }
    }

public class MultithreadComm {

    public static void main(String[] args) {

        SharedThread data = new SharedThread();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("for loop " + i);
                data.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                data.consume();
            }
        });

        producer.start();
        consumer.start();
    }
}