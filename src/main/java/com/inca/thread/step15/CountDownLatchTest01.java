package com.inca.thread.step15;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest01 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		
		for (int i = 0; i < 3; i++) {

			Runnable run=new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 5000));
						System.out.println("线程" + Thread.currentThread().getName() + "准备接受命令");
						cdOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
						Thread.sleep((long) (Math.random() * 5000));
						System.out.println("线程" + Thread.currentThread().getName() + "回应处理结果");
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(run);
		}
		
		

		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
			cdOrder.countDown();

			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令,正在等待结果");
			cdAnswer.await();
			System.out.println("线程" + Thread.currentThread().getName() + "收到所有响应结果");
			service.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
//打印结果:
//	线程pool-1-thread-3准备接受命令
//	线程pool-1-thread-2准备接受命令
//	线程pool-1-thread-1准备接受命令
//	线程main即将发布命令
//	线程main已发送命令,正在等待结果
//	线程pool-1-thread-3已接受命令
//	线程pool-1-thread-1已接受命令
//	线程pool-1-thread-2已接受命令
//	线程pool-1-thread-2回应处理结果
//	线程pool-1-thread-1回应处理结果
//	线程pool-1-thread-3回应处理结果
//	线程main收到所有响应结果

