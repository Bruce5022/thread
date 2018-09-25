package com.inca.thread.step03;

public class SynchronizedTest01 {

	final static Outputer output = new Outputer();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					output.output02("shizhanwei");
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
//	shizhanwei
//	s史战伟
//	史战伟
//	史hizhanwei
//	战伟