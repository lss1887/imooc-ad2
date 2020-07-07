package com.imooc.thread;

public class DemoWaitNotify {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        ConsumerThread consumerThread = new ConsumerThread("consumerThread", consumer);
        Producer producerThread = new Producer("producerThread",consumer);

        consumerThread.start();
        producerThread.start();
    }
}
