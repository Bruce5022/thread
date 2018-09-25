package com.inca.thread.step04;

/**
 * 先让子线程跑,主线程等待,子线程执行完,通知主线程跑,子线程等待,现在可以了
 * 结论:线程同步的代码块,不是在线程上,而是在多线程共同访问的资源上
 * 
 * @author Bruce
 *
 */
public class ThreadCommunicationTest03 {

	final static Business03 business = new Business03();

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
