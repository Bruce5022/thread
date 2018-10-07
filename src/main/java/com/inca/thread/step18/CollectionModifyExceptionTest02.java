package com.inca.thread.step18;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest02 {

	public static void main(String[] args) {
		Collection users = new CopyOnWriteArrayList<>();
		users.add(new User("szw", 111));
		users.add(new User("Bruce", 222));
		users.add(new User("史战伟", 333));
		Iterator iterator = users.iterator();
		System.out.println("修改前的集合数据:" + users.size());
		while (iterator.hasNext()) {

			User next = (User) iterator.next();
			if (next.getName().equals("szw")) {
				users.remove(next);
				System.out.println("修改后的集合数据:" + users.size());
			} else {
				System.out.println(next);

			}
		}
	}
}
//打印结果:
//修改前的集合数据:3
//修改后的集合数据:2
//com.inca.thread.step18.User@3d7a605
//com.inca.thread.step18.User@14750c6