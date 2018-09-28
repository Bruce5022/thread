package com.inca.thread.step12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 改造后的东西,lock先实现同步,这个跟synchronized一样
 * 强调一点,wait和notify必须跟synchronized一块使用,而且,必须用同一个对象 这样才能保证行为一致
 * 
 * @author Bruce
 *
 */
public class Business02 {
	private boolean subExeflag = true;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void sub(int i) {
		lock.lock();
		while (!subExeflag) {
			try {
//				this.wait();
				condition.await();
				//此时一定不能用wait,这东西是跟synchronized一块用的
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 1; j <= 10; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		subExeflag=false;
		condition.signal();
		lock.unlock();
	}

	public void main(int i) {
		lock.lock();
		while (subExeflag) {
			try {
//				this.wait();
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 1; j <= 100; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		subExeflag=true;
		condition.signal();
		lock.unlock();
	}

}
