package com.inca.thread.step06;

public class ThreadScopeData {
	private ThreadScopeData() {
	}

// 单例实现吗?算了
//	private static ThreadScopeData data = null;
//	public static ThreadScopeData getInstance() {
//		if (data == null) {
//			data = new ThreadScopeData();
//		}
//		return data;
//	}
	//方式2
	private static ThreadLocal<ThreadScopeData> threadData = new ThreadLocal<ThreadScopeData>();

	// 这种不加线程同步是可以的,1个线程过来取自己的数据,是空的,new一个自己的数据,就算暂停,下面在放进去,也是可以的
	public static /*synchronized*/ ThreadScopeData getInstance() {
		ThreadScopeData data = threadData.get();
		if (data == null) {
			data = new ThreadScopeData();
			threadData.set(data);
		}
		return data;
	}

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
