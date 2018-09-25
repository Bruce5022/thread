package com.inca.thread.step04;

public class Business02 {

	public synchronized void sub(int i) {
		for (int j = 1; j <= 10; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
	}

	public synchronized void main(int i) {
		for (int j = 1; j <= 100; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
	}
}
