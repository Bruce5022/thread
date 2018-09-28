package com.inca.thread.step13;

import java.util.concurrent.Semaphore;

/*
 * Semaphore可以作为互斥锁来用,但区别是:
 * 释放锁的线程可能不是同一个线程
 */
public class MyTask implements Runnable {
	static Semaphore sp = new Semaphore(2, true);
	
	public void run() {

		try {
			sp.acquire();
			System.out.println(Thread.currentThread().getName() + "执行,当前已" + (2 - sp.availablePermits()) + "个并发");

			Thread.sleep((long) (Math.random() * 10000*10000));
			System.out.println(Thread.currentThread().getName() + "即将离开");
			sp.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

//打印结果:
//	pool-1-thread-1执行,当前已1个并发
//	pool-1-thread-3执行,当前已3个并发
//	pool-1-thread-2执行,当前已2个并发
//	pool-1-thread-3即将离开
//	pool-1-thread-5执行,当前已3个并发
//	pool-1-thread-5即将离开
//	pool-1-thread-9执行,当前已3个并发
//	pool-1-thread-2即将离开
//	pool-1-thread-10执行,当前已3个并发
//	pool-1-thread-1即将离开
//	pool-1-thread-4执行,当前已3个并发
//	pool-1-thread-9即将离开
//	pool-1-thread-6执行,当前已3个并发
//	pool-1-thread-10即将离开
//	pool-1-thread-8执行,当前已3个并发
//	pool-1-thread-4即将离开
//	pool-1-thread-7执行,当前已3个并发
//	pool-1-thread-6即将离开
//	pool-1-thread-7即将离开
//	pool-1-thread-8即将离开
