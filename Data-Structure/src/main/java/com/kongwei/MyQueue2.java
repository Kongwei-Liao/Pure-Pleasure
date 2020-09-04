package com.kongwei;

/**
 * 队列（FIFO）
 * @param <E>
 */
public class MyQueue2<E> {
    
    MyLinkedList<E> list = new MyLinkedList<>();
    int index = 0;  // 下标

    /**
     * 进入队列
     * @param n
     */
    public void in(E n) {
        list.add(n);
        index++;
    }

    /**
     * 移出队列
     * @return
     */
    public E out() {
        if (!list.isEmpty()) {
            index--;
            return list.remove(0);
        }
        return null;
    }
}
