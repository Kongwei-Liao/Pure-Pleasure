Lambda是一个匿名函数，我们可以把Lambda 表达式理解为是一段可以传递的代码
（将代码像数据一样进行传递，这段代码块会在将来的某个时间调用）。可以写出更简洁、更灵活的代码。作为一种更紧凑
的代码风格，使Java的语言表达能力得到了提升。

针对只有一个抽象方法的接口：
1.既然在形参中写清楚了是什么接口，编译器实际上已经知道了接口名称，没有必要再重新写一遍。
2.如果接口中只有一个方法，那么要实现的必然是这个方法，那么方法名称编译器也知道，没有必要再重新写一遍。
3.既然方法的参数和返回值类型在接口中都定义过了，那么编译器依然知道，没有必要再重新写一遍。


在这种情况下，为了简化为传参大量使用的匿名内部类导致代码
- 冗余性的增加以及可读性的下降，lambda表达式诞生了。
- 上一页中简化的写法就是一个Lambda表达式。
- Java从1.8版本中开始对Lambda表达式进行支持。



比如使用一个定制的比较器完成排序。比如想要按照长度而不是默认的字典顺序对字符串进行排序，可以想sort方法中传递
一个Comparator对象。

```java
import java.util.Comparator;
class LengthComparator implements Comparator {
    public int compare(String first,String second) {
        return first.length() - second.length();
    }
}
// ...
Arrays.sort(strings,new LengthComparator());
```

compare方法并不是立即调用。实际上，数组完成排序之前，sort方法会一直调用compare方法，只要元素的顺序不正确就
会重新排列元素。
将比较元素所需的代码段放到sort方法中，这段代码将与其余的排序逻辑集成（可能并不打算重新实现其余这一部分逻辑）


