package com.company.mycontainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {
    volatile List list = new ArrayList();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    c.add(new Object());
                    System.out.println("add "+i);
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (c.size() == 5){
                        break;
                    }
                }
                System.out.println("t2结束");
            }
        }).start();
    }
}
