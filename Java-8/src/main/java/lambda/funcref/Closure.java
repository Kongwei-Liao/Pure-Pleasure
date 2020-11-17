package lambda.funcref;

import java.util.function.Consumer;

public class Closure {
    public static void main(String[] args) {

        int num = 10;

        Consumer<String> consumer = ele -> {

            // num--;   //

            System.out.println(num);
            // 在有些地方的lambda表达式在被调用很久后才会返回，若在此之前num占用空间已经被释放会产生什么样的影响？？

            // num 是这个lambda表达式的一个自由变量。该lambda表达式的数据结构必须存储自由变量的值，这里就是整型10。
            // 可以说他是被lambda表达式捕获的（Captured）


        };

        // num = num + 2;
        // 如果我们把注释放开会报错，告诉我 num 值是 final 不能被改变。这里我们虽然没有标识 num 类型为 final，但是在编译期间虚拟机会帮我们加上
        // final 修饰关键字，这个问题我们在匿名内部类中也会存在，
        consumer.accept("hello");
    }
}
