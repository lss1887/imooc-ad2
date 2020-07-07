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
            synchronized (this){
                if(c.isFlag() == false){
                    try {
                        this.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming");
                c.setFlag(false);
                this.notify();
            }
        }
    }
}
