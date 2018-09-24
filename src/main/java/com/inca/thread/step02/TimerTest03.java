package com.inca.thread.step02;

import java.util.Timer;
import java.util.TimerTask;

/**
 * jdk1.5以前的定时器,异步执行任务
 * @author Bruce
void cancel()
终止此计时器，丢弃任何当前计划的任务。  
int purge() 
从该计时器的任务队列中删除所有取消的任务。  
void schedule(TimerTask task, Date time) 
在指定的时间安排指定的任务执行。  
void schedule(TimerTask task, Date firstTime, long period) 
从指定 的时间开始 ，对指定的任务执行重复的 固定延迟执行 。  
void schedule(TimerTask task, long delay) 
在指定的延迟之后安排指定的任务执行。  
void schedule(TimerTask task, long delay, long period) 
在指定 的延迟之后开始 ，重新执行 固定延迟执行的指定任务。  
void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) 
从指定的时间 开始 ，对指定的任务执行重复的 固定速率执行 。  
void scheduleAtFixedRate(TimerTask task, long delay, long period) 
在指定的延迟之后 开始 ，重新执行 固定速率的指定任务。  
 */
public class TimerTest03 {
	public static void main(String[] args) {
		System.out.println("开始执行...");
		new Timer().schedule(
				new TimerTask() {public void run() {
					System.out.println("哈哈哈");
				}},//任务内容
				1000,// 延迟时间
				2000// 间隔时间
				);
		System.out.println("执行结束!!!");
	}
}
// 打印结果:
//开始执行...
//执行结束!!!
//哈哈哈
//哈哈哈
//哈哈哈
//哈哈哈
//....
//....
