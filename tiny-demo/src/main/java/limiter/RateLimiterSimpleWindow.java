package limiter;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterSimpleWindow {
    // 阈值
    private static final int QPS = 2;
    // 时间窗口（毫秒）
    private static final long TIME_WINDOWS = 1000;
    // 计数器
    private static final AtomicInteger REQ_COUNT = new AtomicInteger();

    private static long START_TIME = System.currentTimeMillis();

    public static boolean tryAcquire() {
        if ((System.currentTimeMillis() - START_TIME) > TIME_WINDOWS) {
            REQ_COUNT.set(0);
            START_TIME = System.currentTimeMillis();
        }
        return REQ_COUNT.incrementAndGet() <= QPS;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(250);
            LocalTime now = LocalTime.now();
            if (!tryAcquire()) {
                System.out.println(now + " 被限流");
            } else {
                System.out.println(now + " 做点什么");
            }
        }
    }
}
