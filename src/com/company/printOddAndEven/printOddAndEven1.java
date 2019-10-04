package com.company.printOddAndEven;

import java.util.concurrent.CountDownLatch;
//
public class printOddAndEven1 {
    static CountDownLatch latch = new CountDownLatch(2);

    private static Object lock = new Object();

    private static volatile Integer i = 0;
    private static final int  TOTAL = 100;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i<=TOTAL){
                    synchronized (lock){
                        if (i%2 == 1){
                                System.out.println("奇线程 " + i++);
                        }else {
                            lock.notifyAll();
                            try{
                                if (i<TOTAL){
                                    lock.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                latch.countDown();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= TOTAL) {
                    synchronized (lock) {
                        if (i % 2 == 0) {
                            System.out.println("偶线程 " + i++);
                        } else {
                            lock.notifyAll();
                            try {
                                if (i<TOTAL) {
                                    lock.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                latch.countDown();
            }
        });

        thread1.start();
        thread2.start();


    }
}
