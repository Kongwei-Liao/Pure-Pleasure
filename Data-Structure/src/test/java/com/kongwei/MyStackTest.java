package com.kongwei;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyStackTest {

    MyStack myStack = new MyStack();

    public @Before void testPush() {
        for (int i = 0; i < 10; i++) {    // 按顺序将0、10、20、30、40、50、60、70、80、90压入栈中
            myStack.push(i * 10);
        }
    }

    public @Test void testPop() {
        for (int i = 10; i > 0; ) {      // 弹出的顺序应该是90、80、70、60、50、40、30、20、10、0
            assertEquals(--i * 10, myStack.pop());
        }
    }

}
