package com.stickpoint.devtools.common.utils;
import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fntp
 */
@SuppressWarnings("unused")
public class ThreadUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2672990615083303115L;

    private static final ExecutorService POOL;

    static {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 10;
        TimeUnit keepAliveTimeUnit = TimeUnit.MINUTES;
        int queSize = 100_000;
        POOL = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize),
                Executors.defaultThreadFactory());
    }

    /**
     * 获取线程池
     * @return 线程池
     */
    public static ExecutorService getPool() {
        return POOL;
    }

}
