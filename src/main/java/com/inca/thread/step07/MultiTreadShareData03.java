package com.inca.thread.step07;

/**
 * 内部类共享成员变量
 * 
 * 
 * @author Bruce
 *
 */
public class MultiTreadShareData03 {

	int j = 0;

	public static void main(String[] args) {
		MultiTreadShareData03 share03 = new MultiTreadShareData03();
		System.out.println("111:"+share03);
		for (int i = 0; i < 2; i++) {
			new Thread(share03.new SubMyMinusRunnable(), "减线程" + i).start();
			new Thread(share03.new SubMyAddRunnable(), "加线程" + i).start();
		}
	}

	public synchronized void increment() {
		System.out.println("222:"+this);
		System.out.print("当前值:" + j + ",");
		j++;
		System.out.println(Thread.currentThread().getName() + "执行加后" + j);
	}

	public synchronized void decrement() {
		System.out.println("333:"+this);
		System.out.print("当前值:" + j + ",");
		j--;
		System.out.println(Thread.currentThread().getName() + "执行减后" + j);
	}

	class SubMyAddRunnable implements Runnable {
		public void run() {
			increment();
		}

	}

	class SubMyMinusRunnable implements Runnable {
		public void run() {
			decrement();
		}

	}

}
