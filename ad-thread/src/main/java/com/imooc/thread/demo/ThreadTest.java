package com.imooc.thread.demo;

public class ThreadTest {

    public static void main(String[] args) {
        //创建资源
        Student s = new Student();

        //设置和获取的类
        SetThread st = new SetThread(s);
        GetThread gt = new GetThread(s);

        //线程类
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(gt);

        //启动线程
        t1.start();
        t2.start();
    }


    //代码中的实体类
   public static class Student {
      String name;
      int age;
      boolean flag; // 默认情况是没有数据，如果是true，说明有数据
  }

    public static class SetThread implements Runnable {

        private Student s;
        private int x = 0;

        public SetThread(Student s) {
            this.s = s;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (s) {
                    //判断有没有
                    if(s.flag){
                        try {
                            s.wait(); //t1等着，释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (x % 2 == 0) {
                        s.name = "林青霞";
                        s.age = 27;
                    } else {
                        s.name = "刘意";
                        s.age = 30;
                    }
                    x++; //x=1

                    //修改标记
                    s.flag = true;
                    //唤醒线程
                    s.notify(); //唤醒t2,唤醒并不表示你立马可以执行，必须还得抢CPU的执行权。
                }
                //t1有，或者t2有
            }


        }
    }
    public static class GetThread implements Runnable {
        private Student s;

        public GetThread(Student s) {
            this.s = s;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (s) {
                    if(!s.flag){
                        try {
                            s.wait(); //t2就等待了。立即释放锁。将来醒过来的时候，是从这里醒过来的时候
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(s.name + "---" + s.age);
                    //林青霞---27
                    //刘意---30

                    //修改标记
                    s.flag = false;
                    //唤醒线程
                    s.notify(); //唤醒t1
                }
            }
        }
    }
}
