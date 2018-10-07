package com.inca.thread.step18;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 多线程操作共同数据,慎用hashmap,可能导致cpu一直瞎忙碌,死循环操作这个map 如果多线程状态需要用map,就用concurrenthashmap
 * 以前的处理方式,现在不建议用
 * 
 * @author Bruce
 *
 */
public class HashMapTest01 {

	public static void main(String[] args) {
		Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<String, Object>());
		System.out.println(synchronizedMap);

	}

}
