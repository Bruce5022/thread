package com.inca.thread.step02;

import java.util.Timer;
import java.util.TimerTask;

/**
 * jdk1.5以前的定时器,异步执行任务
 * @author Bruce
 *
 */
public class TimerTest02 {
	public static void main(String[] args) {
		System.out.println("开始执行...");
		new Timer().schedule(
				new TimerTask() {
					public void run() {
						System.out.println("哈哈哈task thread id :" + Thread.currentThread().getId());
					}},
				1000
				);
		System.out.println("执行结束!!!main thread id："+ Thread.currentThread().getId());
	}
}
// 打印结果:
//开始执行...
//执行结束!!!
//哈哈哈