package com.inca.thread.step12;
/**
 * 跟synchronized一样的,简单改造没新东西,但为什么要用condition呢???
 * 原因:允许多路等待,condition跟锁绑定,可以造出多个
 * @author Bruce
 *
 */
public class ConditionTest02 {

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
