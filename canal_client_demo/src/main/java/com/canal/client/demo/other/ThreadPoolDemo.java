package com.canal.client.demo.other;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {
    static ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static final LinkedBlockingDeque<Runnable> QUEUE = new LinkedBlockingDeque<>();
    public static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(5, 5, 2, TimeUnit.SECONDS, QUEUE, new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            System.out.println("创建新线程:thread-" + counter.get());
            return new Thread(r, "thread-" + counter.getAndIncrement());
        }
    });


    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        strange();
//        normal();
        Thread.sleep(10000);
        System.exit(0);
    }

    public static void normal() {
        THREAD_POOL.submit(new Task());
        THREAD_POOL.submit(new Task());
        THREAD_POOL.submit(new Task());
        THREAD_POOL.submit(new Task());
    }

    public static void strange() throws InterruptedException {
        THREAD_POOL.execute(() -> {
            System.out.println("start threadpool...");
        });
        QUEUE.put(new Task());
        QUEUE.put(new Task());
        QUEUE.put(new Task());
        QUEUE.put(new Task());
    }

}
