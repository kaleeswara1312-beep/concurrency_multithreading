package org.example.Thread;

public class RunnableThread  implements  Runnable{

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("RunnableTask: " + i);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}
