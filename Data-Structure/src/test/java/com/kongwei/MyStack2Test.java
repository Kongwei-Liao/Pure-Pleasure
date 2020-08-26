package com.kongwei;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyStack2Test {
    MyStack2<Integer> myStack2 = new MyStack2<>();

    public @Before void testPush() {
        for(int i = 0;i < 10;i++) {
            myStack2.push(i * 10);
        }
    }

    public @Test void testPop() {
        for(int i = 9;i >= 0;i--) {
            //
            Assert.assertEquals(new Integer(i * 10),myStack2.pop());
        }
    }
}
