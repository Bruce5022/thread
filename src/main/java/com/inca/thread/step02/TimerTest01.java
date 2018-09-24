package com.inca.thread.step02;

import java.util.Timer;
import java.util.TimerTask;

/**
 * jdk1.5以前的定时器创建调用方式
 * @author Bruce
 *
 */
public class TimerTest01 {
	public static void main(String[] args) {
		new Timer().schedule(
				new TimerTask() {public void run() {}},// 指定定时器执行任务
				1000 //时间参数
				);
	}

}
