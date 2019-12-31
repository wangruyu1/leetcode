package com.canal.client.demo.thread;

class 顺序打印三个数 {

    public 顺序打印三个数() {

    }

    private volatile boolean first = false;
    private volatile boolean second = false;
    private volatile boolean third = false;
    private Object lock = new Object();

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            first = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!first) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            second = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!second) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            third = true;
        }
    }

    public void first2(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        first = true;
    }

    public void second2(Runnable printSecond) throws InterruptedException {
        while (!first) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        second = true;
    }

    public void third2(Runnable printThird) throws InterruptedException {
        while (!second) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        third = true;
    }
}