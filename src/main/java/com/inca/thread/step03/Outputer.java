package com.inca.thread.step03;

/**
 * 把多线程操作的公用的代码片段,用synchronized来括起来
 * 多个线程跑起来以后,线程执行的代码随时可能挂起去执行另一个线程
 * 
 * @author Bruce
 *
 */
public class Outputer {
	
	public void output00(String name) {
		int len = name.length();
		for (int i = 0; i < len; i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
	

	public void output01(String name) {
		int len = name.length();
		// name像一把门栓,一个线程进去,就拿走了门栓,其它线程执行过来后,只能等待
		// 只能是唯一的门栓对象,必须是唯一的对象
		synchronized (Outputer.class) {
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
		}
		System.out.println();
	}

	// 方法上的synchronized的锁是this对象,这个锁对象只能在一个地方看门,如果两个方法method1和method2分别
	// 是代码块和方法,都用this对象,也能达到互斥
	public synchronized void output02(String name) {
		int len = name.length();
		for (int i = 0; i < len; i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
	
	/*public synchronized void output03(String name) {
		int len = name.length();
		// name像一把门栓,一个线程进去,就拿走了门栓,其它线程执行过来后,只能等待
		// 只能是唯一的门栓对象,必须是唯一的对象
		synchronized (Outputer.class) {
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
		}
		System.out.println();
	}*///这种极有可能产生死锁
	//什么情况下才能死锁呢???这个问题,后面好好了解,但这里先记住结论
	// 一个方法内,最好只有一个synchronized块,要么是方法,要么是代码块
	
	public void output04(String name) {
		int len = name.length();
		synchronized (this) {
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
		}
		System.out.println();
	}
	
	/**
	 * 静态方法的门栓,不再是this对象,而是对象的字节码了,所以,方法2,方法4跟本方法不能实现互斥
	 * @param name
	 */
	public static synchronized void output05(String name) {
		int len = name.length();
		for (int i = 0; i < len; i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
}
