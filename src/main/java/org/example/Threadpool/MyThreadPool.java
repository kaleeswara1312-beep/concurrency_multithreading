package org.example.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {

    public void executeTasks() {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            MyTask task = new MyTask(i);

            pool.submit(task);
        }

        pool.shutdown();
    }
}