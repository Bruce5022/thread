package com.inca.thread.step11;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读和写的操作需要互斥,就把两个操作封装起来
 * 
 * @author Bruce
 *
 */
public class Queue02 {
	private Object data = null;// 共享数据,只能有一个线程能控制

	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public void get() {
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName() + "开始读...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "已读完:" + data);
		rwl.readLock().unlock();
	}

	public void put(Object data) {
		rwl.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + "开始写...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.data = data;
		System.out.println(Thread.currentThread().getName() + "已写入:" + data);
		rwl.writeLock().unlock();
	}

}
