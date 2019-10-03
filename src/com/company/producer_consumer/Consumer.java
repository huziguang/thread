package com.company.producer_consumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;      //缓冲区
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id="+Thread.currentThread().getId());
        Random r = new Random();       //随机等待时间
        try{
            while(true){
                PCData data = queue.take();    //提取任务
                if (null != data){
                    int re = data.getIntData()*data.getIntData(); //计算平方
                    System.out.println(MessageFormat.format("{0}*{1}={2}",data.getIntData(),data.getIntData(),re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
