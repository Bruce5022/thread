package com.inca.thread.step05;

import java.util.Random;
/**
 * 线程共享变量导致数据混乱引入
 * @author Bruce
 *
 */
public class ShareDataTest01 {
	private static int data = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					data = new Random().nextInt(999 );
					System.out.println(Thread.currentThread().getName() + "存入" + data);
					new ModuleA().get();
					new ModuleB().get();
				}
			}, "线程"+i).start();
		}
		
	}

	static class ModuleA {

		public void get() {
			System.out.println("ModuleA从"+Thread.currentThread().getName() + "获取数据" + data);
		}

	}

	static class ModuleB {
		public void get() {
			System.out.println("ModuleB从"+Thread.currentThread().getName() + "获取数据" + data);
		}
	}
}

//打印结果:
//线程1存入194
//线程3存入194
//线程2存入194
//线程0存入194
//线程4存入435
//ModuleA从线程1获取数据435
//ModuleA从线程2获取数据435
//ModuleA从线程3获取数据435
//ModuleA从线程4获取数据435
//ModuleA从线程0获取数据435
//ModuleB从线程1获取数据435
//ModuleB从线程0获取数据435
//ModuleB从线程3获取数据435
//ModuleB从线程2获取数据435
//ModuleB从线程4获取数据435

