package com.inca.thread.step04;
/**
 * 能实现一个任务执行完之前,另一个任务等待,打印结果不会乱了,怎么实现交替执行呢???
 * @author Bruce
 *
 */
public class ThreadCommunicationTest01 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {

				for (int i = 1; i <= 50; i++) {
					synchronized (Object.class) {
						for (int j = 1; j <= 10; j++) {
							System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
						}
					}
				}
			}
		}, "子线程").start();
		
		for (int i = 1; i <= 50; i++) {
			synchronized (Object.class) {
				for (int j = 1; j <= 100; j++) {
					System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
				}
			}
		}
	}

}
//打印结果:
//	main第29次循环,当前打印 90
//	main第29次循环,当前打印 91
//	main第29次循环,当前打印 92
//	main第29次循环,当前打印 93
//	main第29次循环,当前打印 94
//	main第29次循环,当前打印 95
//	main第29次循环,当前打印 96
//	main第29次循环,当前打印 97
//	main第29次循环,当前打印 98
//	main第29次循环,当前打印 99
//	main第29次循环,当前打印 100
//	子线程第1次循环,当前打印 1
//	子线程第1次循环,当前打印 2
//	子线程第1次循环,当前打印 3
//	子线程第1次循环,当前打印 4
//	子线程第1次循环,当前打印 5
//	子线程第1次循环,当前打印 6
//	子线程第1次循环,当前打印 7
//	子线程第1次循环,当前打印 8