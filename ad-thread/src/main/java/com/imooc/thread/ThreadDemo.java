package com.imooc.thread;

public class ThreadDemo {


    public static void main(String[] args) {

        //自定义线程1
        MyThread mt = new MyThread("myThread");
        mt.start();

        //创建线程方法2
        MyRunnable mr = new MyRunnable();
        Thread t = new Thread(mr,"myRunnableThread");
        t.start();

        //匿名内部类的方式创建
        new Thread(){
            @Override
            public void run(){
                System.out.println("333333");
            }
        }.start();
        //匿名内部类的方式创建2
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(777777);
            }
        }).start();

    }
}
