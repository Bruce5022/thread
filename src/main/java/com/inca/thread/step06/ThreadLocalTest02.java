package com.inca.thread.step06;

import java.util.Random;

/**
 * ThreadLocal到底怎么玩,一个程序运行一个月,有10000个线程访问过,这个集合难道就有那么多数据残留吗,都死了很久的线程的数据,还留着吗?
 * 说明文档:
 * 只要线程存活并且ThreadLocal实例可以访问，每个线程都保存对其线程局部变量副本的隐式引用; 线程消失后，线程本地实例的所有副本
 * 都将被垃圾收集（除非存在对这些副本的其他引用）。 
 * 
 * 既然可以自动回收掉,除去了一丝忧虑.
 * 但如果自己主动remove,何时调用呢?
 * @author Bruce
 *
 */
public class ThreadLocalTest02 {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt(999);
					System.out.println(Thread.currentThread().getName() + "存入" + data);
					ThreadScopeData.getInstance().setAge(data);
					ThreadScopeData.getInstance().setName(Thread.currentThread().getName());
					new ModuleA().get();
					new ModuleB().get();
				}
			}, "线程" + i).start();
		}

	}

	static class ModuleA {
		public void get() {
			int data = ThreadScopeData.getInstance().getAge();
			System.out.println("ModuleA从" + Thread.currentThread().getName() + "获取数据" + data);
		}

	}

	static class ModuleB {
		public void get() {
			int data = ThreadScopeData.getInstance().getAge();
			System.out.println("ModuleB从" + Thread.currentThread().getName() + "获取数据" + data);
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
