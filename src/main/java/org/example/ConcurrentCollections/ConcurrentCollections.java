package org.example.ConcurrentCollections;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

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

public class ConcurrentCollections {
    public static void main(String[] args) throws InterruptedException {
        HashMapDemo hashMapDemo = new HashMapDemo();
        hashMapDemo.demo();

        HashtableDemo hashtableDemo = new HashtableDemo();
        hashtableDemo.demo();

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(32, "Kali");
        treeMap.put(12, "Kumar");
        treeMap.put(4, "King");

        System.out.println(treeMap);

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

        Map<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        for (Integer key : map.keySet()) {

            System.out.println("Key: " + key);

            if (key == 2) {
                map.put(4, "D");   // Modification while iterating
            }
        }

        System.out.println(map);
    }
}
