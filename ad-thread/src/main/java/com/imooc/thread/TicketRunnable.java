package com.imooc.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketRunnable implements Runnable {
    private int ticket = 30;

    @Override
    public void run() {
        Lock lock = new ReentrantLock();
        try {
            while (true) {

                if (ticket > 0) {

                    lock.lock();
                    Thread.sleep(10);
                    System.out.println("线程：" + Thread.currentThread().getName() + "当前第几个:" + ticket);
                    ticket = ticket - 1;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
