package com.canal.client.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    static IntConsumer print = (x) -> {
        System.out.print(x);
    };

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd odd = new ZeroEvenOdd(5);

        new Thread(() -> {
            while (true) {
                try {
                    odd.odd(print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    odd.zero(print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    odd.even(print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private int n;
    private int counter = 1;
    private volatile boolean first = true;
    private volatile boolean second = true;
    private volatile boolean third = false;
    private ReentrantLock lock = new ReentrantLock();
    private Condition firstCondition = lock.newCondition();
    private Condition secondCondition = lock.newCondition();
    private Condition thirdCondition = lock.newCondition();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (counter <= n) {
            lock.lock();
            while (!first) {
                firstCondition.await();
            }
            if (counter > n) {
                first = false;
                second = true;
                third = true;
                secondCondition.signal();
                thirdCondition.signal();
                System.out.println("zero");
                break;
            }
            printNumber.accept(0);
            if (second) {
                secondCondition.signal();
            } else if (third) {
                thirdCondition.signal();
            }
            first = false;
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (counter <= n) {
            lock.lock();
            while (first || !second) {
                secondCondition.await();
            }
            if (counter > n) {
                first = true;
                third = true;
                firstCondition.signal();
                thirdCondition.signal();
                System.out.println("odd");
                break;
            }
            printNumber.accept(counter++);
            first = true;
            second = false;
            third = true;
            firstCondition.signal();
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (counter <= n) {
            lock.lock();
            while (first || !third) {
                thirdCondition.await();
            }
            if (counter > n) {
                second = true;
                first = true;
                firstCondition.signal();
                secondCondition.signal();
                System.out.println("even");
                break;
            }
            printNumber.accept(counter++);
            first = true;
            third = false;
            second = true;
            firstCondition.signal();
            lock.unlock();
        }
    }
}