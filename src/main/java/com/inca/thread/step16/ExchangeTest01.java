package com.inca.thread.step16;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangeTest01 {
	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger<String> ex = new Exchanger<String>();

		Runnable run1 = new Runnable() {
			public void run() {
				try {
					String data1 = "szw";
					System.out.println("线程" + Thread.currentThread().getName() + "准备把" + data1 + "换出去");
					Thread.sleep(10000);
					String data2 = ex.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Runnable run2 = new Runnable() {
			public void run() {
				try {
					String data1 = "史战伟";
					System.out.println("线程" + Thread.currentThread().getName() + "准备把" + data1 + "换出去");
					Thread.sleep(10000);
					String data2 = ex.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable run3 = new Runnable() {
			public void run() {
				try {
					String data1 = "于丁杰";
					System.out.println("线程" + Thread.currentThread().getName() + "准备把" + data1 + "换出去");
					Thread.sleep(10000);
					String data2 = ex.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable run4 = new Runnable() {
			public void run() {
				try {
					String data1 = "ydj";
					System.out.println("线程" + Thread.currentThread().getName() + "准备把" + data1 + "换出去");
					Thread.sleep(10000);
					String data2 = ex.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回" + data2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		service.execute(run1);
		service.execute(run2);
		service.execute(run3);
		service.execute(run4);
	}

}

//打印结果:
//	线程pool-1-thread-1准备把szw换出去
//	线程pool-1-thread-4准备把ydj换出去
//	线程pool-1-thread-3准备把于丁杰换出去
//	线程pool-1-thread-2准备把史战伟换出去
//	线程pool-1-thread-4换回史战伟
//	线程pool-1-thread-3换回szw
//	线程pool-1-thread-2换回ydj
//	线程pool-1-thread-1换回于丁杰

