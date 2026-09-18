package org.example;

import org.example.Thread.MyThread;
import org.example.Thread.RunnableThread;
import org.example.Threadpool.MyThreadPool;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        MyThread myTh = new MyThread();
//
//        System.out.println("Before start:1 " + myTh.getState());
//        myTh.start();
//        System.out.println("After start2: " + myTh.getState());
//
//        RunnableThread runTh = new RunnableThread();
//        Thread th2 = new Thread(runTh);
//        System.out.println("th2 Before start:1 " + th2.getState());
//
//        th2.start();
//        System.out.println("th2 after start2: " + th2.getState());

        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.executeTasks();

    }
}
