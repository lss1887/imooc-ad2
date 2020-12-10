package com.imooc.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {

    public static void main(String[] args) {
    // 创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(2);//包含2个线程对象
        // 创建Runnable实例对象
//        MyRunnable2 r = new MyRunnable2();
    // 从线程池中获取线程对象,然后调用MyRunnable中的run()
        pool.submit(()-> System.out.println("333"));
        pool.submit(()-> System.out.println("44"));
        pool.submit(()-> System.out.println("22"));
    // 再获取个线程对象，调用MyRunnable中的run()
//        pool.submit(r);
//        pool.submit(r);
// 注意：submit方法调用结束后，程序并不终止，是因为线程池,控制了线程的关闭。
// 将使用完的线程又归还到了线程池中
// 关闭线程池
//pool.shutdown();
    }

}