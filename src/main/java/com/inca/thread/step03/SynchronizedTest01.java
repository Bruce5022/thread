package com.inca.thread.step03;
/**
 * 原始写法导致混乱结果，引入线程同步的必要性
 * @author Administrator
 *
 */

public class SynchronizedTest01 {

	final static Outputer output = new Outputer();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output00("shizhanwei");
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output00("史战伟");
				}
			}
		}).start();

	}

}
//打印结果:
//	shizhanwei
//	s史战伟
//	史战伟
//	史hizhanwei
//	战伟