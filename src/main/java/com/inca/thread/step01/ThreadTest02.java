package com.inca.thread.step01;
/**
 * 重写线程的run方法
 * @author Bruce
 *
 */
public class ThreadTest02 {

	public static void main(String[] args) {
		// 1.创建线程
		Thread thread = new Thread() {
			public void run() {
				System.out.println(this.getName()+":执行run方法体完毕!!!");
			};
		};
		// 2.启动线程
		thread.start();
	}

}
//打印结果:
//Thread-0:执行run方法体完毕!!!
