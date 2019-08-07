package com.inca.thread.step20;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class TestReference {

    public static void main(String[] args) throws Exception{

        testPhantomRef();
    }

    private static void testRefQueue1() throws Exception{
        User user = new User(1, "szw");
        ReferenceQueue<User> queue = new ReferenceQueue<User>();
        WeakReference<User> softReference = new WeakReference<User>(user, queue);
        System.out.println(softReference.isEnqueued());
        System.out.println(queue.poll());
        user = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(softReference.isEnqueued());
        System.out.println(queue.poll());
        System.out.println(softReference.isEnqueued());
        System.out.println(queue.poll());
    }

    private static void testWeakMap1() {
        User user = new User(1, "szw");
        Map<String, User> map = new WeakHashMap<>();
        map.put("u1", user);
        System.out.println(map);
        System.gc();
        System.out.println("After GC:");
        System.out.println(map);
        // 当前map存放的是强引用对象,堆中user是没有释放的,u1引用常量池的东西,也是不会回收,gc后依然存在,所以,map对象都存在
    }

    private static void testWeakMap2() {
        // 可以用WeakHashMap保存一些共享数据,长时间不使用,希望gc后清空的话,用这种map
        Map<String, User> map = new WeakHashMap<>();
        map.put(new String("u1"), new User(1, "szw"));
        System.out.println(map);
        System.gc();
        System.out.println("After GC:");
        System.out.println(map);
        // 当前map存放的是匿名对象,堆中user和u1两个都是匿名的,没有引用,gc后堆中释放,map为空
//        {u1=User{id=1, name='szw'}}
//        After GC:
//        {}
    }

    private static void testStrongRef() {
        User user = new User(1, "szw");
        User temp = user;
        user = null;
        System.gc();
        System.out.println("After GC:");
        System.out.println(temp);
    }

    private static void testSoftRef() {
        User user = new User(1, "szw");
        SoftReference<User> softReference = new SoftReference<User>(user);
        user = null;
        System.out.println(softReference.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(softReference.get());

        byte[] bytes = new byte[204000008 * 10200004 * 7];
        System.gc();
        System.out.println("Final GC:");
        System.out.println(softReference.get());
    }

    private static void testWeakRef() {
        User user = new User(1, "szw");
        WeakReference<User> softReference = new WeakReference<User>(user);
        user = null;
        System.out.println(softReference.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(softReference.get());

        byte[] bytes = new byte[204000008 * 10200004 * 7];
        System.gc();
        System.out.println("Final GC:");
        System.out.println(softReference.get());
    }

    private static void testPhantomRef() {
        User user = new User(1, "szw");
        ReferenceQueue<User> queue = new ReferenceQueue<User>();
        PhantomReference<User> phantomReference = new PhantomReference<User>(user,queue);
        System.out.println(phantomReference.get());
        System.out.println(phantomReference.isEnqueued());
        user = null;
        System.out.println(phantomReference.get());
        System.out.println(phantomReference.isEnqueued());
        System.gc();
        System.out.println(phantomReference.isEnqueued());
        System.out.println(phantomReference.get());
        System.out.println(phantomReference.isEnqueued());
        System.out.println(queue.poll());
    }
}
