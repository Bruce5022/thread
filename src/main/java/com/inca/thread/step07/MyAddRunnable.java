package com.inca.thread.step07;

public class MyAddRunnable implements Runnable {
	private ShareData01 data;

	public MyAddRunnable(ShareData01 data) {
		super();
		this.data = data;
	}

	public void run() {
		data.increment();
	}

}
