package com.pputils.thread;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author liaogangwei
 * @ClassName CommonThreadPoolUtil
 * @Time 2020/11/19 18:52
 * @Desc 1、通用线程池工具
 *       2、支持多线程返回结果
 **/
@Service
public class CommonThreadPoolUtil {

    /**
     * 核心线程数(默认初始化为8)
     */
    private int cacheCorePoolSize = 8;

    /**
     * 核心线程控制的最大数目（初始化值160）
     */
    private int maxCorePoolSize = 160;

    /**
     * 队列等待线程数阈值（初始值16）
     */
    private int blockingQueueWaitSize = 16;

    /**
     * 核心线程数自动调整的增量幅度（初始值4）
     */
    private int incrementCorePoolSize = 4;

//    /**
//     * 线程工厂
//     */
//    private ThreadFactory threadFactory = new ThreadFactory() {
//        @Override
//        public Thread newThread(Runnable r) {
//            return null;
//        }
//    };

    /**
     * 初始化线程对象ThreadLocal,重写initialValue()，保证ThreadLocal首次执行get方法时不会null异常
     */
    private ThreadLocal<List<Future<?>>> threadLocal = new ThreadLocal<List<Future<?>>>() {
        @Override
        protected List<Future<?>> initialValue() {
            return new ArrayList<Future<?>>();
        }
    };

    /**
     * 初始化线程池，初始化时的核心线程池大小与最大线程池大小一致
     */
    private ThreadPoolExecutor ThreadPool = new ThreadPoolExecutor(
            cacheCorePoolSize,
            cacheCorePoolSize,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<Runnable>()
    );

    /**
     * dealTask:(线程池执行操作-包含每个进程返回结果). <br/>
     * 1、运用场景：例如，需要同时校验很多不同的逻辑，依赖于获取校验结果响应给用户；
     * 2、具体实现java类：implements 的Callable接口，重写call方法即可，支持返回值
     *
     * @author liaogangwei
     * @param callable
     * @return
     */
    public Map<String, Object> dealTask(Callable<?> callable) {

        try {
            // 尝试动态扩充核心线程池的容量
            dynamicTuningPoolSize();
            // 执行线程业务逻辑及获取返回结果
            Future<?> result = ThreadPool.submit(callable);
            // 获取当前进程的局部变量
            List<Future<?>> threadLocalResult = threadLocal.get();
            // 叠加主进程对应的多个进程处理结果
            threadLocalResult.add(result);
            // 设置最新的threadLocal变量到当前主进程
            threadLocal.set(threadLocalResult);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResp("线程池发生异常-Future", null);
        }
        return successResp(Thread.currentThread().getName());
    }

    /**
     * dealTask:(线程池执行操作-不包含每个进程返回结果).
     * 1、运用场景：例如，不依赖于响应给用户执行结果的业务逻辑 ；
     * 2、具体实现java类：implements 的 Runnable接口，重写run方法，没有返回值
     *
     * @author liaogangwei
     * @param runnable
     * @return Map<String, Object>
     */
    public Map<String, Object> dealTask(Runnable runnable) {

        try {
            // 动态更改核心线程数大小
            dynamicTuningPoolSize();
            // 执行线程业务逻辑
            ThreadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResp("线程池发生异常", null);
        }
        return successResp(null);
    }

    /**
     * obtainTaskFuture:(获取线程池执行结果：此为阻塞线程，即所有线程都执行完成才能获取结果，故应将执行时间稍长的业务逻辑先执行，减少等待时间).
     * 此方法只能调用一次，即调用之后清除 ThreadLocal 变量，以便于同一进程再次调用线程池获取最新的执行结果以及释放内存， 防止内存泄露
     *
     * @author liaogangwei
     */
    public Map<String, Object> obtainTaskFuture() {

        List<Future<?>> threadLocalResult = null;
        try {
            // 获取当前进程变量
            threadLocalResult = threadLocal.get();
            if (threadLocalResult == null || threadLocalResult.size() == 0) {
                return errorResp("获取线程池执行结果为空", null);
            } else {
                return successResp(threadLocalResult);
            }
        } catch (Exception e) {
            return errorResp("获取线程池执行结果发生异常:" + e.getMessage(), null);
        } finally {
            // 1、释放内存；2、防止主进程再次调用线程池方法时对结果互有影响。
            threadLocal.remove();
        }
    }

    /**
     * dynamicTuningPoolSize:(动态改变核心线程数). <br/>
     * 如果等待队列中的线程队列过长，尝试扩充线程池的大小
     * @author liaogangwei
     */
    private void dynamicTuningPoolSize() {

        // 队列等待任务数（此为近似值，故采用>=判断）
        int queueSize = ThreadPool.getQueue().size();
        // 动态更改核心线程数大小
        if (queueSize >= blockingQueueWaitSize) {
            // 核心线程数小于设定的最大线程数才会自动扩展线程数
            if (cacheCorePoolSize <= maxCorePoolSize) {
                // 原有核心线程数
                int corePoolSize = ThreadPool.getCorePoolSize();
                // 将要累积的核心线程数
                int currentcorePoolSize = corePoolSize + incrementCorePoolSize;
                ThreadPool.setCorePoolSize(currentcorePoolSize);
                ThreadPool.setMaximumPoolSize(currentcorePoolSize);
                cacheCorePoolSize = currentcorePoolSize;
                System.out.println("动态改变线程池大小：原核心线程池数目为：" + corePoolSize + ";现累加为：" + currentcorePoolSize);
            } else {
                System.out.println("动态改变线程池大小：核心线程池数目已累加为：" + cacheCorePoolSize + "；不会继续无限增加");
            }
        }
    }

    /**
     * 获取核心线程数 getCacheCorePoolSize:().
     *
     * 完成单元测试 testGetCacheCorePoolSize()
     * @author liaogangwei
     * @return int
     */
    public int getCacheCorePoolSize() {
        return ThreadPool.getCorePoolSize();
    }

    /**
     * 设置核心线程数 setCacheCorePoolSize:(). <br/>
     *
     * 完成单元测试 testSetCacheCorePoolSize()
     * @author liaogangwei
     * @param cacheCorePoolSize
     */
    public void setCacheCorePoolSize(int cacheCorePoolSize) {
        ThreadPool.setCorePoolSize(cacheCorePoolSize);
        ThreadPool.setMaximumPoolSize(cacheCorePoolSize);
        this.cacheCorePoolSize = cacheCorePoolSize;
    }

    /**
     * successResp:(正确响应信息).
     *
     * @author liaogangwei
     * @param data
     * @return
     */
    private Map<String, Object> successResp(Object data) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("status", "0");
        result.put("data", data);
        return result;
    }

    /**
     * errorResp:(错误响应信息).
     *
     * @author liaogangwei
     * @param errorMsg
     * @param data
     * @return
     */
    public Map<String, Object> errorResp(String errorMsg, Object data) {
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put("status", "1");
        result.put("msg", errorMsg);
        result.put("data", data);
        return result;
    }
}
