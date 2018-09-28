package com.inca.thread.step12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 改造后的东西,lock先实现同步,这个跟synchronized一样
 * 强调一点,wait和notify必须跟synchronized一块使用,而且,必须用同一个对象
 * 这样才能保证行为一致
 * 
 * @author Bruce
 *
 */
public class Business01 {

	Lock lock = new ReentrantLock();

	/*private boolean subExeflag = true;*/

	public /* synchronized */ void sub(int i) {
		lock.lock();
		// if变while也可以的,while第一次false等待,然后,被唤醒后判断是true,就接着执行了,也是走两次
		// 项目中,因为文档说明的假唤醒的情况,以后都用while吧
//		if (!subExeflag) {
/*		while (!subExeflag) {
			try {
//				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		for (int j = 1; j <= 10; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		/*subExeflag = false;*/
		//this.notify();
		lock.unlock();
	}

	public /* synchronized */ void main(int i) {
		lock.lock();
//			if (subExeflag) {
		/*while (subExeflag) {
			try {
//				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		for (int j = 1; j <= 100; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		/*subExeflag = true;*/
//		this.notify();
		lock.unlock();
	}

}
