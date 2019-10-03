package com.company.mycontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MyContainer5 {
    List list = new ArrayList();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer5 c = new MyContainer5();
        Semaphore s = new Semaphore(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1启动");
                    s.acquire();
                    for (int i =0; i<10;i++){
                        c.add(new Object());
                        System.out.println("add "+i);
                        if (c.size() == 5){
                            s.release();
                            try{
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try{
                                s.acquire();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try{
                            TimeUnit.SECONDS.sleep(1);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2启动");
                try {
                    s.acquire();
                    System.out.println("t2结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                s.release();
            }
        }).start();
    }

}
