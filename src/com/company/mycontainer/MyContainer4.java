package com.company.mycontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyContainer4 {
    List list = new ArrayList();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer4 c =  new MyContainer4();
        CyclicBarrier cyclic = new CyclicBarrier(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                try {
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("t2结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1启动");
                for (int i =0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add "+i);
                    if (c.size() == 5){
                        try {
                            cyclic.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
