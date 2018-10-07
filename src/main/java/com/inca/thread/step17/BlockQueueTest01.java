package com.inca.thread.step17;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 固定队列大小,如果不能再放数据,就阻塞等待,取走一个再 放
 * @author Bruce
 *
 */
public class BlockQueueTest01 {

	public static void main(String[] args) {
		final BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(3);
		for (int i = 0; i < 2; i++) {

			new Thread() {
				public void run() {
					try {
						while (true) {
							Thread.sleep((long) (Math.random() * 1000));
							System.out.println(Thread.currentThread().getName() + "准备放数据...");
							queue.put(1l);
							System.out.println(Thread.currentThread().getName() + "已经放了数据,队列目前有" + queue.size() + "个数据");

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}.start();
		}
		new Thread() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "准备取数据...");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "已经取走数据,队列目前有" + queue.size() + "个数据");
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}.start();
	}

}
//打印结果:
//	Thread-0准备放数据...
//	Thread-0已经放了数据,队列目前有1个数据
//	Thread-0准备放数据...
//	Thread-0已经放了数据,队列目前有2个数据
//	Thread-0准备放数据...
//	Thread-0已经放了数据,队列目前有3个数据
//	Thread-1准备放数据...
//	Thread-2准备取数据...
//	Thread-2已经取走数据,队列目前有2个数据
//	Thread-1已经放了数据,队列目前有3个数据
//	Thread-0准备放数据...
//	Thread-1准备放数据...
//	Thread-2准备取数据...
//	Thread-2已经取走数据,队列目前有2个数据
//	Thread-0已经放了数据,队列目前有3个数据
//	Thread-0准备放数据...
//	Thread-2准备取数据...
//	Thread-2已经取走数据,队列目前有2个数据
//	Thread-1已经放了数据,队列目前有3个数据
//	Thread-1准备放数据...
//	Thread-2准备取数据...
//	Thread-2已经取走数据,队列目前有2个数据
//	Thread-0已经放了数据,队列目前有3个数据
//	Thread-0准备放数据...
//	Thread-2准备取数据...
//	Thread-2已经取走数据,队列目前有2个数据
//	Thread-1已经放了数据,队列目前有3个数据
