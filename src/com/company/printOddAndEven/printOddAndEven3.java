package com.company.printOddAndEven;

import java.util.concurrent.CountDownLatch;

public class printOddAndEven3 {

    private static final int TOTAL = 100;
    private static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread thread1 = new Thread(()->{
            while (count <  TOTAL){
                if (count%2 == 0){
                    System.out.println("偶线程 "+count);
                    count++;
                }
            }
            latch.countDown();
        });
        Thread thread2 = new Thread(()->{
            while (count < TOTAL){
                if (count%2 == 1){
                    System.out.println("奇线程 "+count);
                    count++;
                }
            }
            latch.countDown();
        });
        thread1.start();
        thread2.start();
        latch.await();

    }
}
