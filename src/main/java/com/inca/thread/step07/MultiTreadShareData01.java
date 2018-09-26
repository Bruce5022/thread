package com.inca.thread.step07;

/**
 * 共享数据给每个线程去执行
 * @author Bruce
 *
 */
public class MultiTreadShareData01 {

	static ShareData01 data = new ShareData01();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					data.decrement();
				}
			}
		}, "减线程1").start();
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					data.decrement();
				}
			}
		}, "减线程2").start();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					data.increment();
				}
			}
		}, "加线程1").start();
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					data.increment();
				}
			}
		}, "加线程2").start();
	}
}
