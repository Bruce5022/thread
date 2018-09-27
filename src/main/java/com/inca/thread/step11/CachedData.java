package com.inca.thread.step11;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 可以理解为代理
 * @author Bruce
 *
 */
class CachedData {
	Object data;
	volatile boolean cacheValid;
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	void processCachedData() {
		rwl.readLock().lock();
		// 有没缓存user呢?
		if (!cacheValid) {
			// Must release read lock before acquiring write lock
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			// Recheck state because another thread might have acquired
			// write lock and changed state before we did.
			if (!cacheValid) {
				//如果没有,查出来
//          data = ...
				// 有数据了
				cacheValid = true;
			}
			// Downgrade by acquiring read lock before releasing write lock
			rwl.readLock().lock();
			rwl.writeLock().unlock(); // Unlock write, still hold read
		}

		// 直接有数据的话,可以使用数据
//     use(data);
		rwl.readLock().unlock();
	}
}
// 所有的线程,同时来访问缓存数据
// 因为是缓存的,所以,可以直接挂读锁,加入5个人读,进来3个
// 最先一个人执行到缓存检查,是未加载
// 这时候把读锁归还,获取写锁,这也说明,读锁被占用时,写锁是空的,互斥的
// 获得写锁后,锁上代码,其它读锁的兄弟停下来,这个线程来做点加载动作
// 加载完了,缓存生效
// 这时可能其它线程也读到这里了,因为读锁不互斥
// 所以取数据之前,再检查
// 后面的获取到写锁,并锁住后,再申请读锁的话,其实是把写锁给降低级别为更新锁了
// 释放更新锁,这里就是技巧问题
// 释放完读锁,后面的线程可以执行了,就算获取到写锁,有了数据,也就不做操作,开始出去了