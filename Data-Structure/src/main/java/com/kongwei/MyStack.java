package com.kongwei;

/**
 * 栈结构（FILO）
 * @param <E>
 */
public class MyStack<E> {

    private E[] elements;
    private int i = 0;  // 当前栈高度，默认为0

    public MyStack() {
        this.elements = (E[]) new Object[1];
    }

    /**
     * 调整栈表容量
     * @param size
     */
    private void resize(int size) {
        E[] temp = (E[]) new Object[size];
        for (int n = 0; n < i; n++) {
            temp[n] = elements[n];
        }
        elements = temp;
    }

    /**
     * 调整栈表容量
     */
    private void judgeSize() {
        if (i >= elements.length) {
            resize(2 * elements.length);
        } else if (i > 0 && i <= elements.length / 2) {
            resize(elements.length / 2);
        }
    }

    /**
     * 入栈
     * @param element
     */
    public void push(E element) {
        judgeSize();
        try {
            elements[i++] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出栈
     * @return
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = elements[--i];
        elements[i] = null;
        return element;
    }

    /**
     *
     * @return
     */
    public int size() {
        return i;
    }

    /**
     * 判空
     * @return
     */
    public boolean isEmpty() {
        return i == 0;
    }
}
