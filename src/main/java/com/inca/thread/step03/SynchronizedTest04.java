package com.inca.thread.step03;
/**
 * 同时调用静态synchronize的方法，也是可以的
 * @author Bruce
 *
 */
public class SynchronizedTest04 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					Outputer.output05("shizhanwei");
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					Outputer.output05("史战伟");
				}
			}
		}).start();

	}

}
//打印结果:
