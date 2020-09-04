package com.kongwei;

/**
 * 队列（FIFO）
 * @param <E>
 */
public class MyQueue<E> {

    E[] elements;
    int i = 0; // 数组下标


    public MyQueue() {
        elements = (E[]) new Object[1];
    }

    /**
     * 入队列
     * @param element
     */
    public void in(E element) {
        judgeSize();
        try {
            elements[i++] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出队列
     * @return
     */
    public E out() {

        int index = 0;
        E temp = elements[0];
        for (int j = 1; j < i; j++) {
            elements[j - 1] = elements[j];
            index++;
        }
        i = index;
        return temp;
    }

    /**
     * 扩充队列容量
     */
    private void judgeSize() {
        if (i >= elements.length) {
            resize(2 * elements.length);
        } else if (i > 0 && i <= elements.length / 2) {
            resize(elements.length / 2);
        }
    }

    /**
     * 调整队列容量
     * @param size
     */
    private void resize(int size) {
        E[] temp = (E[]) new Object[size];
        for (int n = 0; n < i; n++) {
            temp[n] = elements[n];
        }
        elements = temp;
    }

    public int size() {
        return i;
    }

    public boolean isEmpty() {
        return i == 0;
    }
}
