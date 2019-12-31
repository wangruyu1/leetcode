package com.canal.client.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class 两个线程循环打印 {
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition fooCondition = lock.newCondition();
    private Condition barCondition = lock.newCondition();
    private volatile boolean foo = true;

    public 两个线程循环打印(int n) {
        this.n = n;
    }


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!foo) {
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            foo = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (foo) {
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo = true;
        }
    }

    public void foo2(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //获取锁
            lock.lock();
            //该不该我执行
            while (!foo) {
                //等待知道被唤醒
                fooCondition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            //不该我执行了
            foo = false;
            //唤醒指定的条件去等待锁
            barCondition.signal();
            //释放锁
            lock.unlock();
        }
    }

    public void bar2(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (foo) {
                barCondition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo = true;
            fooCondition.signal();
            lock.unlock();
        }
    }
}