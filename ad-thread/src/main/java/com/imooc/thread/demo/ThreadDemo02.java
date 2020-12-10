package com.imooc.thread.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }
        ExecutorService pool = Executors.newFixedThreadPool(10);
        MyRunnable03 runnable03 = new MyRunnable03(list);
        long startTime = System.currentTimeMillis();
        pool.submit(runnable03);
        pool.submit(runnable03);
        pool.submit(runnable03);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        long endTime = System.currentTimeMillis();
        System.out.println("totalTime :{}"+(endTime-startTime));


    }
}
