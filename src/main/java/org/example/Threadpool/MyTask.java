package org.example.Threadpool;

public class MyTask implements Runnable {

    private int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {

        System.out.println(
                "Task " + taskId +
                        " is running by " +
                        Thread.currentThread().getName()
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Task " + taskId +
                        " completed by " +
                        Thread.currentThread().getName()
        );
    }
}