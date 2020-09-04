package com.kongwei;

import com.kongwei.MyLinkedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyLinkedListTest {

    MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

    public @Test void testAddAndRemove() {   // 在链表首插入节点，从表首删除元素
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        assertEquals(1, myLinkedList.remove(0).intValue());
        assertEquals(2, myLinkedList.remove(0).intValue());
        assertEquals(3, myLinkedList.remove(0).intValue());
        // assertEquals(4,myLinkedList.remove(0).intValue());
        assertEquals(4, myLinkedList.remove(0).intValue());
        // assertEquals(1,myLinkedList.size);
    }

    public @Test(expected = ArrayIndexOutOfBoundsException.class) void testRemoveException() {
        myLinkedList.add(1);
        assertEquals(4, myLinkedList.remove(1).intValue());  // An exception should be thrown in this line.
    }
}
