package com.kongwei;

/**
 * 链表
 * @param <E>
 */
public class MyLinkedList<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 链表尾部添加元素
     * @param e E
     * @return boolean
     */
    public boolean add(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    /**
     * 获取指定索引位置的节点 Node<E>
     * @param index 索引
     * @return Node<E>
     */
    Node<E> node(int index) {   // 结点的索引定位
        Node<E> x;
        if (index < (size >> 1)) {  // 由前半部分，从前面往后查找 index
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {                    // 由后半部分，从后面往前查找 index
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    /**
     * 删除指定位置index的元素
     * @param index 索引
     * @return E
     */
    public E remove(int index) {
        if(index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        final Node<E> current = node(index);
        final E element = current.item;     // 当前节点元素
        final Node<E> next = current.next;  // 下一节点
        final Node<E> prev = current.prev;  // 前一节点

        if (prev == null) {     // 当前移除节点为头节点，重置头结点
            first = next;
        } else {                // 当前要移除的节点不是首节点
            prev.next = next;
            current.prev = null;
        }

        if (next == null) {     // 当前要移除的节点是尾节点，更新尾节点
            last = prev;
        } else {                // 当前要移除的节点不是尾节点
            next.prev = prev;
            current.next = null;
        }

        current.item = null;
        size--;
        return element;
    }

}
