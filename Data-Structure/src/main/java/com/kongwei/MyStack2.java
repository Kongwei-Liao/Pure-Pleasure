package com.kongwei;

import java.util.ArrayList;
import java.util.List;

public class MyStack2<T> {

    List<T> list = new ArrayList<T>();
    int index = 0; //下标

    //入栈
    public void push(T n){
        list.add(n);
        index++;
    }

    //出栈
    public T pop(){
        return list.remove(--index);
    }
}
