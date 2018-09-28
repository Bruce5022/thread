package com.inca.thread.step12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多路可阻塞队列的源码实现分析
 * 
 * 
 * @author Bruce
 *
 */
class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				// 如果已经满了,当前线程加入不满的队列,等待不满队列的通知 
				notFull.await();
			}
			items[putptr] = x;
			if (++putptr == items.length) {
				// 如果当前放入后,是100超出了,下次放到0的位置
				putptr = 0;
			}
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		try {
			BoundedBuffer boundedBuffer = new BoundedBuffer();
			boundedBuffer.put(1);
			boundedBuffer.put(2);
			boundedBuffer.put(3);
			boundedBuffer.put(4);
			System.out.println(boundedBuffer.take());
			System.out.println(boundedBuffer.take());
			System.out.println(boundedBuffer.take());
			System.out.println(boundedBuffer.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}