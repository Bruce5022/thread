package com.inca.thread.step03;
/**
 * 这种情况是会出问题的,因为方法1用的代码块门栓是Class字节码,而方法2用的是this对象
 * @author Bruce
 *
 */
public class SynchronizedTest02 {

	final static Outputer output = new Outputer();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output01("shizhanwei");
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output02("史战伟");
				}
			}
		}).start();

	}

}
//打印结果:
//史sh战伟
//史izhanwei
//shizhanwei
//s战伟
//史战伟