package com.imooc.thread;

public class MyThread  extends  Thread{

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("执行run method()"+this.getName());
    }
}
