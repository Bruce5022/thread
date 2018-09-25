package com.inca.thread.step05;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * map实现数据共享
 * @author Bruce
 *
 */
public class ShareDataTest02 {
	private static Map<String,Integer> threadData=new HashMap<String,Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt(999);
					System.out.println(Thread.currentThread().getName() + "存入" + data);
					threadData.put(Thread.currentThread().getName(), data);
					new ModuleA().get();
					new ModuleB().get();
				}
			}, "线程"+i).start();
		}
		
	}

	static class ModuleA {
		public void get() {
			int data = threadData.get(Thread.currentThread().getName());
			System.out.println("ModuleA从"+Thread.currentThread().getName() + "获取数据" + data);
		}

	}

	static class ModuleB {
		public void get() {
			int data = threadData.get(Thread.currentThread().getName());
			System.out.println("ModuleB从"+Thread.currentThread().getName() + "获取数据" + data);
		}
	}
}
//打印结果:
//线程1存入542
//线程4存入889
//线程0存入753
//线程3存入223
//线程2存入962
//ModuleA从线程4获取数据889
//ModuleA从线程1获取数据542
//ModuleA从线程2获取数据962
//ModuleA从线程3获取数据223
//ModuleA从线程0获取数据753
//ModuleB从线程1获取数据542
//ModuleB从线程0获取数据753
//ModuleB从线程3获取数据223
//ModuleB从线程4获取数据889
//ModuleB从线程2获取数据962
