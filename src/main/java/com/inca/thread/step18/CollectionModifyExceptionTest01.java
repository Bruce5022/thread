package com.inca.thread.step18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionModifyExceptionTest01 {

	public static void main(String[] args) {
		Collection users = new ArrayList<>();
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
//Exception in thread "main" java.util.ConcurrentModificationException
//at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:937)
//at java.base/java.util.ArrayList$Itr.next(ArrayList.java:891)
//at com.inca.thread.step18.CollectionModifyExceptionTest.main(CollectionModifyExceptionTest.java:18)
