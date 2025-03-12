package lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class TinyLock {

    AtomicBoolean flag = new AtomicBoolean(false);
    Thread owner = null;

    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());

    class Node{
        Node pre;
        Node next;
        Thread thread;
    }

    public void lock(){
        if (flag.compareAndSet(false,true)){
            // 拿到锁了
            System.out.println(Thread.currentThread().getName() + "直接拿到锁");
            owner = Thread.currentThread();
            return;
        }
        Node current = new Node();
        current.thread = Thread.currentThread();
        while (true){
            Node currentTail = tail.get();
            if (tail.compareAndSet(currentTail,current)){
                current.pre = currentTail;
                currentTail.next = current;
                System.out.println(Thread.currentThread().getName() + "成功把自己加入了链表尾");
                break;
            }
        }
        while (true){
            // 需要满足条件才可以唤醒
            if (current.pre == head.get() && flag.compareAndSet(false,true)){
                owner = Thread.currentThread();
                head.set(current);
                current.pre.next = null;
                current.pre = null;
                System.out.println(Thread.currentThread().getName() + "被唤醒后拿到了锁");
                return;
            }
            LockSupport.park();

        }
    }

    public void unlock(){
        if (Thread.currentThread() != this.owner){
            throw new IllegalStateException("当前线程不持有这把锁！");
        }
        Node headNode = head.get();
        Node next = headNode.next;
        flag.set(false); // 释放锁了，接下来的代码都是有多线程竞争的
        if (next != null){
            System.out.println(Thread.currentThread().getName() + "唤醒了" + next.thread.getName());
            LockSupport.unpark(next.thread);
        }
    }
}
