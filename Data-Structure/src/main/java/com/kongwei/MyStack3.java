package com.kongwei;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 栈结构（FILO）：使用两个队列实现FILO
 * @param <E>
 */
public class MyStack3<E> {

    Queue<E> queueA = new ArrayDeque<E>();
    Queue<E> queueB = new ArrayDeque<E>();

    /**
     * 压栈
     * @param n
     */
    public void push(E n) {
        queueA.add(n);
    }

    /**
     * 出栈
     * @return
     */
    public E pop() {
        // 如果queueA为空，queueB有元素， 将queueB的元素依次放入queueA中，直到最后一个元素，我们弹出。
        if (queueA.isEmpty()) {
            while (queueB.size() > 1) {
                queueA.add(queueB.poll());// poll()移出并返回队列的头元素,如果队列为空，则返回null
            }
            return queueB.poll();
        }
        if (queueB.isEmpty()) {
            while (queueA.size() > 1) {
                queueB.add(queueA.poll());
            }
            return queueA.poll();
        }
        return null;
    }
}
