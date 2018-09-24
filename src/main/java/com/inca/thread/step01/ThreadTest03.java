package com.inca.thread.step01;
/**
 * 另外一种方式创建启动线程
 * 更加面向对象,线程就是执行单元
 * 数据是另一个对象
 * @author Bruce
 *
 */
public class ThreadTest03 {

	public static void main(String[] args) {
		// 1.创建线程
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				System.out.println(Thread.currentThread().getName()+":执行run方法体完毕!!!");
			}
		},"订单线程");
		// 2.启动线程
		thread.start();
	}

}
//打印结果:
//订单线程:执行run方法体完毕!!!
