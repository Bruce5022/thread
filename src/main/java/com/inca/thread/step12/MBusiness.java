package com.inca.thread.step12;
/**
 * 
 * 这个是模板,将要被改造
 * @author Bruce
 *
 */
public class MBusiness {

	private boolean subExeflag = true;

	public synchronized void sub(int i) {
		// if变while也可以的,while第一次false等待,然后,被唤醒后判断是true,就接着执行了,也是走两次
		// 项目中,因为文档说明的假唤醒的情况,以后都用while吧
//		if (!subExeflag) {
		while (!subExeflag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 1; j <= 10; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		subExeflag = false;
		this.notify();
	}
	

	public synchronized void main(int i) {
//			if (subExeflag) {
		while (subExeflag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 1; j <= 100; j++) {
			System.out.println(Thread.currentThread().getName() + "第" + i + "次循环,当前打印 " + j);
		}
		subExeflag = true;
		this.notify();
	}
	
	
	
}
