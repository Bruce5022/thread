package com.inca.thread.step11;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

	private static Map<String, Object> cache = new HashMap<String, Object>();
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public static void main(String[] args) {

	}

	/**
	 * 这样性能下降,有这个数据,返回就行,没这个数据,再查数据库,synchronized会导致多个线程直接挂起
	 * 
	 * @param key
	 * @return
	 */
//	public synchronized static Object getData(String key) {
//		Object object = cache.get(key);
//		if (object == null) {
//			object = queryDB();
//		}
//		return object;
//	}

	public static Object getData(String key) {
		rwl.readLock().lock();
		Object object = cache.get(key);
		if (object == null) {
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			if (object == null) {
				object = queryDB();
			}
			rwl.writeLock().unlock();
			rwl.readLock().lock();
		}
		rwl.readLock().unlock();
		return object;
	}

	private static Object queryDB() {
		return "real data";
	}

}
