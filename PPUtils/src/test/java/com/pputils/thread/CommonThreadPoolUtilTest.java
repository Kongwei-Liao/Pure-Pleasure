package com.pputils.thread;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.Callable;

public class CommonThreadPoolUtilTest {

    @Test
    public void testGetCacheCorePoolSize() {
        CommonThreadPoolUtil threadPoolUtil = new CommonThreadPoolUtil();
        Assert.assertEquals(8, threadPoolUtil.getCacheCorePoolSize());
    }

    @Test
    public void testSetCacheCorePoolSize() {
        CommonThreadPoolUtil threadPoolUtil = new CommonThreadPoolUtil();
        threadPoolUtil.setCacheCorePoolSize(10);
        Assert.assertEquals(10, threadPoolUtil.getCacheCorePoolSize());
    }

    @Test
    public void testDealTask() {
        CommonThreadPoolUtil threadPoolUtil = new CommonThreadPoolUtil();

        Map<String, Object> result = threadPoolUtil.dealTask(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                while (true) {

                }
            }
        });

        Assert.assertEquals("0",result.get("status"));

    }


    @Test
    public void testDynamicTuningPoolSize() {
        CommonThreadPoolUtil threadPoolUtil = new CommonThreadPoolUtil();

        for (int i = 0; i < 33; i++) {
            threadPoolUtil.dealTask(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    while (true) {

                    }
                }
            });
        }

        Assert.assertEquals(20, threadPoolUtil.getCacheCorePoolSize());
    }



}
