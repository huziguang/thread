package com.company.printOddAndEven;

import java.util.concurrent.atomic.AtomicInteger;

public class printOddAndEven2 {
    private static final int TOTAL = 100;
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            while(atomicInteger.get()<TOTAL){
                if (atomicInteger.get()%2==0){
                    System.out.println("偶线程 "+atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(()->{
            while (atomicInteger.get()<TOTAL){
                if (atomicInteger.get()%2==0){
                    System.out.println("奇线程 "+atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
