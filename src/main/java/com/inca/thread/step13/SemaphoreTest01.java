package com.inca.thread.step13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreTest01 {
	private static ExecutorService service = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			service.execute(new MyTask());
		}

	}

}
