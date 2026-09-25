package org.example.ConcurrentCollections;

import java.util.*;
import java.util.concurrent.*;

class HashtableDemo {

    static Hashtable<Integer, Integer> table = new Hashtable<>();

    public void demo() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                table.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                table.put(i, i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(table.size());
    }
}

class HashMapDemo {

    static Map<Integer, Integer> map = new HashMap<>();

     public void demo() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(i, i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.size());
    }
}

class ArrayListDemo {

//    static List<String> list = new ArrayList<>();
    static List<String> list = new CopyOnWriteArrayList<>();

    public void test() throws InterruptedException {

        // Writer
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                list.add("Value-" + i);
                System.out.println("Writer added: Value-" + i);
            }
        });

        // Reader
        Thread reader = new Thread(() -> {
            for (String value : list) {
                System.out.println("Reader read: " + value);
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        System.out.println("Final List: " + list.get(3));
    }
}

class BlockingqueueDemo {
//
    public void test() {
        // Basically if the two consumers try to get the queue and try to remove means,
//        it will throw an nosuchelementexception error. To avoid this we need to go
//        with the Blockingqueue concept.

//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.add(100);
//
//        Runnable consumerTask = () -> {
//
//            if (!queue.isEmpty()) {
//
//                System.out.println(
//                        Thread.currentThread().getName()
//                                + " found item"
//                );
//
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//
//                int value = queue.remove();
//
//                System.out.println(
//                        Thread.currentThread().getName()
//                                + " consumed: " + value
//                );
//            }
//        };
//
//        Thread t1 = new Thread(
//                consumerTask, "Consumer-1"
//        );
//
//        Thread t2 = new Thread(
//                consumerTask, "Consumer-2"
//        );
//
//        t1.start();
//        t2.start();


//        Queue<Integer> queue = new LinkedList<>();
//
//        Thread producer = new Thread(() -> {
//
//            try {
//                Thread.sleep(5600);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            for (int i = 1; i <= 5; i++) {
//
//                queue.add(i);
//
//                System.out.println(
//                        "Produced: " + i
//                );
//            }
//        });
//
//        Thread consumer = new Thread(() -> {
//
//            for (int i = 1; i <= 5; i++) {
//
//                if (!queue.isEmpty()) {
//
//                    int value = queue.remove();
//
//                    System.out.println(
//                            "Consumed: " + value
//                    );
//                }
//            }
//        });
//
//        producer.start();
//        consumer.start();

        // Arrayblockingqueue
//        BlockingQueue<Integer> queue =
//                new ArrayBlockingQueue<>(3);
//
//        // Producer
//        Thread producer = new Thread(() -> {
//
//            try {
//
//                for (int i = 1; i <= 5; i++) {
//
//                    System.out.println(
//                            "Producing: " + i
//                    );
//
//                    queue.put(i);
//
//                    System.out.println(
//                            "Produced: " + i
//                    );
//                    Thread.sleep(500);
//                }
//
//            } catch (InterruptedException e) {
//
//                Thread.currentThread().interrupt();
//            }
//        });
//
//
//        // Consumer
//        Thread consumer = new Thread(() -> {
//
//            try {
//
//                for (int i = 1; i <= 5; i++) {
//
//                    System.out.println(
//                            "Consumer waiting..."
//                    );
//
//                    int value = queue.take();
//
//                    System.out.println(
//                            "Consumed: " + value
//                    );
//
//                    Thread.sleep(1000);
//                }
//
//            } catch (InterruptedException e) {
//
//                Thread.currentThread().interrupt();
//            }
//        });
//
//
//        producer.start();
//        consumer.start();

        // Linkedblockingqueue
        BlockingQueue<String> queue =
                new LinkedBlockingQueue<>(3); // capacity = 3

        // Producer
        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {

                try {
                    queue.put("Task-" + i);

                    System.out.println(
                            "Produced: Task-" + i
                    );

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Consumer
        Thread consumer = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {

                try {
                    String task = queue.take();

                    System.out.println(
                            "Consumed: " + task
                    );

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class ConcurrentLinkedQueueDemo {

    public void test() {

        Queue<String> queue =
                new ConcurrentLinkedQueue<>();

        // Producer
        Thread producer = new Thread(() -> {

            for (int i = 1; i <= 10; i++) {

                queue.offer("Task-" + i);

                System.out.println(
                        "Produced: Task-" + i
                );

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });


        // Consumer
        Thread consumer = new Thread(() -> {

            for (int i = 1; i <= 10; i++) {

                String task;

                // Keep checking until a task is available
                while ((task = queue.poll()) == null) {

                    System.out.println(
                            "Queue empty - consumer continues checking..."
                    );

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                System.out.println(
                        "Consumed: " + task
                );
            }
        });


        producer.start();
        consumer.start();
    }
}

public class ConcurrentCollections {
    public static void main(String[] args) throws InterruptedException {
//        HashMapDemo hashMapDemo = new HashMapDemo();
//        hashMapDemo.demo();
//
//        HashtableDemo hashtableDemo = new HashtableDemo();
//        hashtableDemo.demo();
//
//        TreeMap<Integer, String> treeMap = new TreeMap<>();
//        treeMap.put(32, "Kali");
//        treeMap.put(12, "Kumar");
//        treeMap.put(4, "King");
//
//        System.out.println(treeMap);

        // Map with concurrentmodifition exception
//        Map<Integer, String> map = new Hashtable<>();
//
//        map.put(1, "A");
//        map.put(2, "B");
//        map.put(3, "C");
//
//        for (Integer key : map.keySet()) {
//
//            System.out.println("Key: " + key);
//
//            if (key == 2) {
//                map.put(4, "D");   // Modification while iterating
//            }
//        }

//        Map<Integer, String> map = new ConcurrentHashMap<>();
//
//        map.put(1, "A");
//        map.put(2, "B");
//        map.put(3, "C");
//
//        for (Integer key : map.keySet()) {
//
//            System.out.println("Key: " + key);
//
//            if (key == 2) {
//                map.put(4, "D");   // Modification while iterating
//            }
//        }
//
//        System.out.println(map);


//        ArrayListDemo arrayListDemo = new ArrayListDemo();
//        arrayListDemo.test();

        BlockingqueueDemo blockingqueueDemo = new BlockingqueueDemo();
        blockingqueueDemo.test();

//        ConcurrentLinkedQueueDemo concurrentLinkedQueueDemo = new ConcurrentLinkedQueueDemo();
//        concurrentLinkedQueueDemo.test();
    }
}
