package com.kongwei;

public class MyQueue {
    int[] a = new int[5];
    int i = 1; //数组下标

    // 入队列
    public void in(int m){
        a[i++] = m;
    }

    // 出队列
    public int out(){
        int index = 0;
        int temp = a[1];
        for(int j=1;j<i;j++){
            a[j-1] = a[j];
            index++;
        }
        i = index;
        return temp;
    }
}
