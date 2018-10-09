package com.inca.thread.step10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 更加面向对象的锁Lock引入
 * 
 * @author Bruce
 *
 */
public class Outputer {
	Lock lock = new ReentrantLock();

	public void output(String name) {
		lock.lock();
		int len = name.length();
		for (int i = 0; i < len; i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
		lock.unlock();
	}

}
