package com.imooc.thread;

public class Producer  extends  Thread{
    private Consumer c;

    public Producer(String name,Consumer c) {
        super(name);
        this.c = c;
    }

    @Override
    public void  run(){
        while (true){
            synchronized (c){
                if(c.isFlag() == false){
                    try {
                        c.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming");
                c.setFlag(false);
                c.notify();
            }
        }
    }
}
