package com.kongwei;

import java.util.ArrayList;
import java.util.List;

public class MyQueue2<T> {
    List<T> list = new ArrayList<T>();
    int index = 0;  //下标

    //入队
    public void in(T n){
        list.add(n);
        index++;
    }

    //出队
    public T out(){
        if(!list.isEmpty()){
            index--;
            return list.remove(0);
        }
        return null;
    }
}
