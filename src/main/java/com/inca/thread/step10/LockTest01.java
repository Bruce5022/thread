package com.inca.thread.step10;
/**
 * 方法2和方法4用的是同一个门栓,是没问题的
 * @author Bruce
 *
 */
public class LockTest01 {

	final static Outputer output = new Outputer();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output("shizhanwei");
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output("史战伟");
				}
			}
		}).start();

	}

}
//打印结果:
//正常