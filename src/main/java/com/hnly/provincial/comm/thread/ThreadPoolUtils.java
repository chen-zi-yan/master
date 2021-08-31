package com.hnly.provincial.comm.thread;

import java.util.concurrent.*;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-07-24
 */
public class ThreadPoolUtils {

    private static final ExecutorService threadPool;

    static {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 5;
        TimeUnit keepAliveTimeUnit = TimeUnit.MINUTES;
        //队列大小
        int queSize = 100000;

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, keepAliveTimeUnit, new LinkedBlockingQueue<>(queSize)
                , threadFactory);
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
