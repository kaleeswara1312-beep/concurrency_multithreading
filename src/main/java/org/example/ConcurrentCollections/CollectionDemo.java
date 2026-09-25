package org.example.ConcurrentCollections;

import java.util.ArrayList;
import java.util.List;

public class CollectionDemo {
    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();
        List<Integer> arrList2 = new ArrayList<>();

        arrList.add(12);
        arrList.add(45);
        arrList.add(12);

        for (Integer e : arrList){
            System.out.println("e " + e.compareTo(12));

            if(e == 45){
                arrList2.add(e);
            }
        }

        System.out.println(arrList2);
    }
}
