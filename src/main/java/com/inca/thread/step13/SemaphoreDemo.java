package com.inca.thread.step13;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	/**
	 * 打饭窗口
	 * 2：   2个打饭窗口
	 * true：公平队列-FIFO
	 */
	static Semaphore semaphore = new Semaphore(2, true);
}
