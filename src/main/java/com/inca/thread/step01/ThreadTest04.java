package com.inca.thread.step01;
/**
 * 看懂真正要执行的代码
 * 如果重写了run并且传入了runnable对象,会怎么执行?
 * 因为重写run方法,执行的是自己的,跟runnable没有关系了
 * @author Bruce
 *
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		// 1.创建线程
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				System.out.println(Thread.currentThread().getName()+":执行run方法体完毕!!!");
			}
		},"订单线程") {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+":执行run方法体完毕>>>");
			}
			
		};
		// 2.启动线程
		thread.start();
	}

}
//打印结果:
//订单线程:执行run方法体完毕>>>
