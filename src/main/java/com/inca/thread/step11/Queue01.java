package com.inca.thread.step11;

/**
 * 不加锁的情况,多线程读写没限制,谁都能执行代码
 * @author Bruce
 *
 */
public class Queue01 {
	private Object data = null;// 共享数据,只能有一个线程能控制

	public void get() {
		System.out.println(Thread.currentThread().getName() + "开始读...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "已读完:" + data);
	}

	public void put(Object data) {
		System.out.println(Thread.currentThread().getName() + "开始写...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.data = data;
		System.out.println(Thread.currentThread().getName() + "已写入:" + data);
	}

}
