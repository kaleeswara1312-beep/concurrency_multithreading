package org.example.Thread;

class SharedData {
    volatile boolean flag = false;
}

class MyThread1 extends Thread {
    SharedData data;

    MyThread1(SharedData data) {
        this.data = data;
    }

    public void run() {
        while (!data.flag) {
            System.out.println("While loop inside");
        }
        System.out.println("Flag changed!");
    }
}

public class VolatileEx{
    public static void main(String[] args) throws Exception {
        SharedData data = new SharedData();
        MyThread1 t = new MyThread1(data);
        t.start();

        Thread.sleep(1000);
        data.flag = true;
    }
}
