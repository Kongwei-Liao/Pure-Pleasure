package com.kongwei;

public class MyStack {

    private final int[] a;
    private int i = 0;

    public MyStack() {
        a = new int[5];     // a = new T[5];    类型参数不能直接被实例化
    }

    public MyStack(int n) {
        a = new int[n];
    }


    // 入栈
    public void push(int n){
        try {
            a[i++] = n;
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    // 出栈
    public int pop(){
        if(i>0){
            return a[--i];
        }
        return -1;
    }
}
