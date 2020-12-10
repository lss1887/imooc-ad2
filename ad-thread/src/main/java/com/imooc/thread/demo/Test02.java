package com.imooc.thread.demo;

import java.util.concurrent.*;

public class Test02 {
    public static void main(String[] args) {
//线程池的使用,简单,记产生线程池的工具类Executors,然后线程池对象提交任务执行即可
// ExecutorService pool = Executors.newFixedThreadPool(2);//pool线程池对象,2表示池里面有2个线程
// pool.submit(new Runnable() {
// @Override
// public void run() {
// System.out.println("剪头发啦");
// }
// });
//
// pool.submit(new Runnable() {
// @Override
// public void run() {
// System.out.println("剪头发啦2");
// }
// });
//
// pool.submit(new Runnable() {
// @Override
// public void run() {
// System.out.println("剪头发啦3");
// }
// });

//线程池里面线程执行完任务,程序没有停止,线程没有销毁而是回到线程里面,继续等待执行任务,线程可以重复利用的
// Future<?> f = pool.submit(new Runnable() {
// @Override
// public void run() {
// System.out.println("剪头发啦2");
// }
// });//f是Future未来对象对象名
//
// try {
//// System.out.println(f);//java.util.concurrent.FutureTask@63d4e2ba
// System.outprintln.(f.get());//提交任务对应重写方法的返回值,没有返回值返回null表示
// } catch (InterruptedException e) {
// e.printStackTrace();
// } catch (ExecutionException e) {
// e.printStackTrace();
// }

        ExecutorService pool = Executors.newFixedThreadPool(2);//pool线程池对象,2表示池里面有2个线程
// pool.submit(new Callable<Object>() {
// @Override
// public Object call() {
// System.out.println("剪完头发");
// return 888;
// }
// });

        Future<Object> f2 = pool.submit(() -> {
            System.out.println("剪完头发");
            return 888;
        });
        try {
            System.out.println(f2.get());//888,未来对象的得到方法对应的是提交任务重写方法返回值,call方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//程序没有停止,线程池的线程没有销毁,可以重复利用
//把线程池关了
        pool.shutdown();//关了程序停止了,建议不要这么做,不能做事情了,否则报错:RejectedExecutionException,拒绝执行异常

        pool.submit(new Callable<Object>() {//报错,线程池已经关了,不能执行任务了
            @Override
            public Object call() {
                System.out.println("do something");
                return 888;
            }
        });
    }
}
