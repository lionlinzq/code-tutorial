package pers.lionlinzq.tiny.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    System.out.println("i=" + i);
                    System.out.println("阻塞一下！");
                    LockSupport.park();
                }
            }
            System.out.println("重新打印！");
        });
        thread.start();
        Thread.sleep(2000);
        LockSupport.unpark(thread);
    }
}
