package com.inca.thread.step01;
/**
 * 线程引入
 * @author Bruce
 *
 */
public class ThreadTest01 {

	public static void main(String[] args) {
		// 1.创建线程
		Thread thread=new Thread();
		// 2.启动线程
		thread.start();
		// 3.启动线程后,会执行Thread类的run方法,上面的代码,没有重写run,执行默认
//		private Runnable target;
//		public void run() {
//	        if (target != null) {
//	            target.run();
//	        }
//	    }
		// 4.因为默认的方法,需要的runnable没给赋值,因此,上面的代码的run方法,没执行任何逻辑

	}

}
