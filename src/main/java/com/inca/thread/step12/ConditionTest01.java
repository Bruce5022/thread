package com.inca.thread.step12;

/**
 * 用lock改造后,先实现互斥运行
 * @author Bruce
 *
 */
public class ConditionTest01 {

	final static Business01 business = new Business01();

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
