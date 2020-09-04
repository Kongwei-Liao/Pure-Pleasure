package com.kongwei;

import java.util.Stack;

/**
 * 队列特性（FIFO）：使用两个栈来实现队列
 * @param <E>
 */
public class MyQueue3<E> {

    Stack<E> stackA = new Stack<E>();
    Stack<E> stackB = new Stack<E>();

    /**
     * 入队
     * @param n
     */
    public void in(E n) {
        stackA.push(n);
    }

    /**
     * 出队
     * @return
     */
    public E out() {
        if (stackB.isEmpty()) {
            while (stackA.size() > 0) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }
}
