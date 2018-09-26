package com.inca.thread.step07;

public class ShareData01 {

	int j = 0;

	public synchronized void increment() {
		System.out.print("当前值:"+j+",");
		j++;
		System.out.println(Thread.currentThread().getName()+"执行加后"+j);
	}

	public synchronized void decrement() {
		System.out.print("当前值:"+j+",");
		j--;
		System.out.println(Thread.currentThread().getName()+"执行减后"+j);
	}

}
