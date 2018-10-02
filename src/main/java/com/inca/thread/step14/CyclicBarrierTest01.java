package com.inca.thread.step14;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest01 {
	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {

			Runnable run=new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 5000));
						System.out.println("线程" + Thread.currentThread().getName() + "到达集合点1,当前已"
								+ (cb.getNumberWaiting() + 1) + "在等待");
						cb.await();
						Thread.sleep((long) (Math.random() * 5000));
						System.out.println("线程" + Thread.currentThread().getName() + "到达集合点2,当前已"
								+ (cb.getNumberWaiting() + 1) + "在等待");
						cb.await();
						Thread.sleep((long) (Math.random() * 5000));
						System.out.println("线程" + Thread.currentThread().getName() + "到达集合点3,当前已"
								+ (cb.getNumberWaiting() + 1) + "在等待");
						cb.await();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			};
			service.execute(run);
		}
	}

}
