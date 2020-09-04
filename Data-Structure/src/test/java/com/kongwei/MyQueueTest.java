package com.kongwei;

import com.kongwei.MyQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyQueueTest {

    MyQueue myQueue = new MyQueue();

    public @Before void testIn() {
        for (int i = 0; i < 10; i++) {
            myQueue.in(i);      // 0, 1, 2, 3, ...., 9 按顺序入队列
        }
    }

    public @Test void testOut(){
        for (int i = 0; i < 10; i++) {
            assertEquals(i, myQueue.out());     // 应按 0, 1, 2, 3, ...., 9 按照入队列顺序出队列
        }
    }
}
