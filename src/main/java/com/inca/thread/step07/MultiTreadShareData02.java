package com.inca.thread.step07;

/**
 * 共享数据给每个线程去执行
 * 
 * @author Bruce
 *
 */
public class MultiTreadShareData02 {

	public static void main(String[] args) {
		ShareData01 data = new ShareData01();
		new Thread(new MyMinusRunnable(data), "减线程1").start();
		new Thread(new MyMinusRunnable(data), "减线程2").start();
		new Thread(new MyAddRunnable(data), "加线程1").start();
		new Thread(new MyAddRunnable(data), "加线程2").start();
	}
}
