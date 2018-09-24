package com.inca.thread.step02;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 间隔多久后,开始执行 然后交替时间执行,怎么做呢?
 * 两种方式:
 * 1.两种炸弹
 * 2.全局变量
 * @author Bruce
 *
 */
public class TimerTest04 {
	static int cnt = 0;
	public static void main(String[] args) {
		System.out.println("开始执行...");
		
		class MyTask extends TimerTask {
			

			@Override
			public void run() {
				cnt = (cnt+1) % 2;
				System.out.println("哈哈哈");
				new Timer().schedule(new MyTask(), // 任务
						2000+2000*cnt// 延迟时间1s
						);
			}
		}

		new Timer().schedule(
				new MyTask(), // 任务
				1000// 延迟时间1s
				);
		System.out.println("执行结束!!!");
		int i=0;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+++"s");
		}
	}
}
// 打印结果:
