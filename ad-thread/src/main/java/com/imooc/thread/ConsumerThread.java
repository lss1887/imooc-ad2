package com.imooc.thread;

public class ConsumerThread extends Thread {
    private  Consumer c;

    public ConsumerThread(String name,Consumer c) {
        super(name);
        this.c = c;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (this){
                if(c.isFlag() == true){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("start producing");
                    count ++;
                    c.setFlag(true);
                    System.out.println("completely");
                    this.notify();
                }
            }
        }
    }
}
