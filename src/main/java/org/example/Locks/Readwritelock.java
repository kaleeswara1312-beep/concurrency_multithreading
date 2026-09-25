package org.example.Locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedData {

    private String data = "Hello";

    private final ReadWriteLock lock =
            new ReentrantReadWriteLock();

    void read() {

        lock.readLock().lock();

        try {
            System.out.println(
                    Thread.currentThread().getName()
                            + " reading: " + data
            );
        } finally {
            lock.readLock().unlock();
        }
    }

    void write(String data) {

        lock.writeLock().lock();

        try {
            this.data = data;

            System.out.println(
                    Thread.currentThread().getName()
                            + " writing: " + data
            );
        } finally {
            lock.writeLock().unlock();
        }
    }
}

public class Readwritelock {

    public static void main(String[] args) {

        SharedData data = new SharedData();

        Thread reader1 = new Thread(data::read, "Reader-1");
        Thread reader2 = new Thread(data::read, "Reader-2");

        Thread writer = new Thread(
                () -> data.write("Java"),
                "Writer"
        );

        reader1.start();
        reader2.start();
        writer.start();
    }
}
