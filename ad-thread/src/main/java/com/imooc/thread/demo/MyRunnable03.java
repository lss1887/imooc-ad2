package com.imooc.thread.demo;

import java.util.List;

public class MyRunnable03 implements Runnable{
    private List<Integer> list;


    public MyRunnable03(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
       list.forEach(e -> System.out.println("::"+e));
    }
}
