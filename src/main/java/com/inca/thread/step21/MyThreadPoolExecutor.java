package com.inca.thread.step21;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor {

    private int corePoolSize;// 线程池大小
    private int maximumPoolSize;// 线程池最大大小
    private int workQueueSize;

    private LinkedBlockingQueue<Runnable> workQueue = null;

    public MyThreadPoolExecutor(int corePoolSize,
                                int maximumPoolSize,
                                int workQueueSize) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueueSize = workQueueSize;
        // 初始化并没创建线程,那就是提交的时候
        workQueue = new LinkedBlockingQueue<Runnable>(workQueueSize);

    }

    // 1.线程池线程干什么?
    public class Worker extends Thread {
        Runnable firstTask = null;
        public Worker(Runnable firstTask) {
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            try {
                // 执行用户提交的runnable,用户提交的任务在哪里呢?
                // 怎么保证线程不死呢?
                Runnable task = firstTask;
                // workQueue.take() 是阻塞的,如果取不到
                while (task != null || (task = workQueue.take()) != null) {
                    task.run();
                    task = null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private AtomicInteger currentPoolSize = new AtomicInteger(0);

    // 2.execute提交任务
    // 注意:这里可以同时被多个线程调用
    public void execute(Runnable command) throws Exception {
        // 注意:多个线程调用过来,可能出现问题,核心线程数量5个,6个线程同一时间,执行到这行代码,同时判断成立,导致6次调用.

        // 废弃方案
        /*if (currentPoolSize < corePoolSize) {
            new Thread(() -> {

                // 这样run方法执行完,线程就没了
            }).start();
            currentPoolSize++;
        }*/
        // 用JUC基础类型原子操作封装
        // 1.判断corePoolSize,如果核心线程未满,则创建一个work(本质就是一个线程)
        if (currentPoolSize.get() < corePoolSize) {
            // 原子操作,只要调用,就是原子+1,保证都是递增,不覆盖
            if (currentPoolSize.incrementAndGet() <= corePoolSize) {
                new Worker(command).start();
                return;
            } else {
                // 如果发现 超过了核心线程数量的大小限制
                currentPoolSize.decrementAndGet();
            }
        }

        // 2.提交到任务仓库,是否达到线程池最大数量?成功放入表示没满,结束
        if (workQueue.offer(command)) {
            return;
        }

        // 3.如果放入失败,已经满了呢?
        if (currentPoolSize.get() < maximumPoolSize) {
            // 原子操作,只要调用,就是原子+1,保证都是递增,不覆盖
            if (currentPoolSize.incrementAndGet() <= maximumPoolSize) {
                new Worker(command).start();
                return;
            } else {
                // 如果发现 超过了核心线程数量的大小限制
                currentPoolSize.decrementAndGet();
            }
        }

        // 4.拒绝处理这个任务
        throw new Exception("拒绝执行");

    }
}
