package com.purepleasure.pepsicola.reflection;

import java.lang.reflect.Field;
import java.net.URL;

public class ReflectTry {

    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }

        ClassLoader loader = ReflectTry.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

        // 同过Class.forName("类全限定名")
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.purepleasure.pepsicola.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);// class com.purepleasure.pepsicola.reflection.Person

        Field[] fields = aClass.getFields(); // 获取 public 修饰的成员
        for (Field field : fields) {
            System.out.println(field);// public java.lang.Boolean com.purepleasure.pepsicola.reflection.Person.gender
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);// public java.lang.Boolean com.purepleasure.pepsicola.reflection.Person.gender
        }

//        // 类名.class
//        Class<?> bClass = Person.class;
//        System.out.println(bClass);// class com.purepleasure.pepsicola.reflection.Person
//
//        // 对象.getClass()
//        Person person = new Person();
//        Class<?> cClass = person.getClass();
//        System.out.println(cClass);// class com.purepleasure.pepsicola.reflection.Person
//
//        System.out.println(aClass == bClass);// true
//        System.out.println(cClass == bClass);// true
//
//        // 结论：同一个字节码文件（*.class）再一次程序运行过程中，只会被加载一次。无论通过哪种方式获取到的Class对象都是同一个。

    }

}
