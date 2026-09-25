package org.example.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {

    private int count = 0;

    private final Lock lock = new ReentrantLock();

    void increment() {

        lock.lock();

        try {
            count++;
            System.out.println(
                    Thread.currentThread().getName() + " : " + count
            );
        } finally {
            lock.unlock();
        }
    }
}

public class Reentrantlock {

    public static void main(String[] args) {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}
