package com.inca.thread.step21;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, // 核心线程数量5
                10,// 最大数量10
                5, // 超出核心线程数量的线程存活时间5秒
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE) // 无解队列
        );

        // 提交15个执行时间需要3秒的任务,看超过大小的2个,对应的处理情况
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("开始执行:" + n);
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("执行结束:" + n);
                } catch (InterruptedException e) {
                    System.out.println("error:" + e.getMessage());
                }
            });
            System.out.println("任务提交成功:" + i);
        }
        // 查看线程数量,查看队列等待数量
        TimeUnit.MICROSECONDS.sleep(500L);
        System.out.println("前:当前线程池线程数量为:" + threadPoolExecutor.getPoolSize());
        System.out.println("前:当前线程池等待线程数量为:" + threadPoolExecutor.getQueue().size());

        // 等待15秒,查看线程数量和队列数量(理论上,会被超出核心线程数量的线程自动销毁)
        TimeUnit.MICROSECONDS.sleep(15000000L);
        System.out.println("后:当前线程池线程数量为:" + threadPoolExecutor.getPoolSize());
        System.out.println("后:当前线程池等待的线程数量为:" + threadPoolExecutor.getQueue().size());
    }
}
