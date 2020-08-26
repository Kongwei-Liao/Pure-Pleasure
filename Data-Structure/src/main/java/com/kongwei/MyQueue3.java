package com.kongwei;

import java.util.Stack;

public class MyQueue3<T> {
    Stack<T> stackA = new Stack<T>();
    Stack<T> stackB = new Stack<T>();

    //入队
    public void in(T n) {
        stackA.push(n);
    }

    //出队
    public T out() {
        if(stackB.isEmpty()){
            while (stackA.size() > 0) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }
}
