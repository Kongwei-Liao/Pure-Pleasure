package com.kongwei;

/**
 * 栈结构（FILO）
 * @param <E>
 */
public class MyStack2<E> {

    MyLinkedList<E> list = new MyLinkedList<>();
    int index = 0; // 下标

    /**
     * 压栈
     * @param n
     */
    public void push(E n) {
        list.add(n);
        index++;
    }

    /**
     * 出栈
     * @return
     */
    public E pop() {
        return list.remove(--index);
    }
}
