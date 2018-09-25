package com.inca.thread.step04;
/**
 public final void wait()导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法。 换句话说，这个方法的行为就好像简单地执行呼叫wait(0) 。 
当前的线程必须拥有该对象的显示器。 该线程释放此监视器的所有权，并等待另一个线程通知等待该对象监视器的线程通过调用notify方法或notifyAll方法notifyAll 。 然后线程
等待，直到它可以重新获得监视器的所有权并恢复执行。 

像在一个参数版本中，中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用： 
wait必须在synchronized中使用,而且,必须跟synchronized的锁,是同一个对象,不然,会报错

  synchronized (obj) {
         while (<condition does not hold>)
             obj.wait();
         ... // Perform action appropriate to condition
     } 
 * 
 * @author Bruce
 *
 */
public class Business03 {

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
