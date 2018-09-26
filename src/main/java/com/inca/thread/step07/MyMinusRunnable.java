package com.inca.thread.step07;

public class MyMinusRunnable implements Runnable {
	private ShareData01 data;

	public MyMinusRunnable(ShareData01 data) {
		super();
		this.data = data;
	}

	public void run() {
		data.decrement();
	}

}
