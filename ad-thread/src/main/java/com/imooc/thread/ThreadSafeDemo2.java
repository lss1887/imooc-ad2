package com.imooc.thread;

public class ThreadSafeDemo2 {

    public static void main(String[] args) {
        //创建线程任务对象
        //搞一个接口实现类对象,给所有线程共享的
        TicketRunnable ticket = new TicketRunnable();

        Thread t1 = new Thread(ticket, "线程1");
        Thread t2 = new Thread(ticket, "线程2");
        Thread t3 = new Thread(ticket, "线程3");


        t1.start();//run方法
        t2.start();
        t3.start();
    }
}
