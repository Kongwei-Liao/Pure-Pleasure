package com.kongwei;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyStack3Test {
    MyStack3<Integer> myStack3 = new MyStack3<>();

    public @Before void testPush() {
        for (int i = 0; i < 10; i++) {
            myStack3.push(i * 10);
        }
    }

    public @Test void testPop() {
        for (int i = 9; i >= 0; i--) {
            //
            Assert.assertEquals(new Integer(i * 10), myStack3.pop());
        }
    }
}
