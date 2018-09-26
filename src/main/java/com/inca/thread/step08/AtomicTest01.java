package com.inca.thread.step08;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * Atomic包下的 东西,是线程安全的,可以研究下
 * @author Bruce
 *
 */
public class AtomicTest01 {
	private static AtomicInteger data = new AtomicInteger(0);

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				public void run() {
					while(true) {
						System.out.print("当前值:" + data.get() + ",");
						data.incrementAndGet();
						System.out.println(Thread.currentThread().getName()+"执行加后"+data.get());
					}
				}
			}, "加线程"+i).start();
		}
	}

}
