package com.inca.thread.step04;

/**
 * 更加面向对象的东西,把相关的东西,封装成类,然后实现同步互斥,这是第二种方式,但依然没解决问题,如何实现线程通信呢?
 * @author Bruce
 *
 */
public class ThreadCommunicationTest02 {

	final static Business02 business = new Business02();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}, "子线程").start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

}
