package org.example.Thread;

class ProductStock {

    private int stock =1;

    public synchronized void buyProduct(){

        if(stock > 0){
            System.out.println(Thread.currentThread().getName() + " bought a stock");
            stock--;
        } else{
            System.out.println(Thread.currentThread().getName() + " already out of stock");
        }
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        ProductStock productStock = new ProductStock();

        Runnable runnable = () -> productStock.buyProduct();

        for (int i = 0; i < 3; i++) {
            int id = i+1;
            Thread thread = new Thread(runnable, "Customer " + id);
            thread.start();
        }
    }
}
